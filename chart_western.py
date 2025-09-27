import math
from datetime import datetime

import pytz
import swisseph as swe


def to_ut_julian_day(local_dt: datetime) -> float:
    """Convert timezone-aware local datetime to UT Julian Day (Gregorian)."""
    if local_dt.tzinfo is None:
        raise ValueError("Datetime must be timezone-aware")
    ut_dt = local_dt.astimezone(pytz.utc)
    hour_decimal = ut_dt.hour + ut_dt.minute / 60.0 + ut_dt.second / 3600.0
    return swe.julday(ut_dt.year, ut_dt.month, ut_dt.day, hour_decimal, swe.GREG_CAL)


SIGNS = [
    "Aries",
    "Taurus",
    "Gemini",
    "Cancer",
    "Leo",
    "Virgo",
    "Libra",
    "Scorpio",
    "Sagittarius",
    "Capricorn",
    "Aquarius",
    "Pisces",
]


def format_longitude(lon: float) -> str:
    lon = lon % 360.0
    sign_index = int(lon // 30)
    deg_within = lon - sign_index * 30.0
    deg = int(deg_within)
    minute = int((deg_within - deg) * 60.0)
    second = int(round((((deg_within - deg) * 60.0) - minute) * 60.0))
    if second == 60:
        second = 0
        minute += 1
    if minute == 60:
        minute = 0
        deg += 1
    return f"{deg:02d}°{minute:02d}'{second:02d}" + f" {SIGNS[sign_index]}"


def compute_chart():
    # INPUTS: birth details
    # Using Bhilwara city center as proxy for Mangrop, Rajasthan, India
    # If you have exact Mangrop coordinates, substitute below.
    latitude = 25.3467  # degrees North
    longitude = 74.6364  # degrees East
    tz = pytz.timezone("Asia/Kolkata")

    # April 13, 1991, 14:20 local (IST)
    local_dt = tz.localize(datetime(1991, 4, 13, 14, 20, 0))

    # Swiss Ephemeris setup (use Moshier to avoid needing ephemeris files)
    swe.set_ephe_path(".")
    flags = swe.FLG_MOSEPH | swe.FLG_SPEED

    jd_ut = to_ut_julian_day(local_dt)

    # Houses (Placidus)
    house_system = b"P"
    cusps, ascmc = swe.houses_ex(jd_ut, latitude, longitude, house_system)

    # Ascendant and MC
    asc = ascmc[0]
    mc = ascmc[1]

    # Planets to compute
    planet_ids = [
        swe.SUN,
        swe.MOON,
        swe.MERCURY,
        swe.VENUS,
        swe.MARS,
        swe.JUPITER,
        swe.SATURN,
        swe.URANUS,
        swe.NEPTUNE,
        swe.PLUTO,
    ]
    planet_names = [
        "Sun",
        "Moon",
        "Mercury",
        "Venus",
        "Mars",
        "Jupiter",
        "Saturn",
        "Uranus",
        "Neptune",
        "Pluto",
    ]

    positions = []
    for pid, name in zip(planet_ids, planet_names):
        xx, retflag = swe.calc_ut(jd_ut, pid, flags)
        lon = xx[0]
        positions.append((name, lon))

    # Output
    print("Birth details: 1991-04-13 14:20 IST, Bhilwara, Rajasthan, India")
    print(f"Latitude: {latitude:.4f} N, Longitude: {longitude:.4f} E")
    print("")
    print("Angles:")
    print(f"  Ascendant: {format_longitude(asc)}")
    print(f"  Midheaven: {format_longitude(mc)}")
    print("")
    print("House cusps (Placidus):")
    for i, cusp in enumerate(cusps[1:13], start=1):
        print(f"  House {i:2d}: {format_longitude(cusp)}")
    print("")
    print("Planets (tropical, geocentric):")
    for name, lon in positions:
        print(f"  {name:8s}: {format_longitude(lon)}")


if __name__ == "__main__":
    compute_chart()

