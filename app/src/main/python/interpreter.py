# import instaloader
#
# from instaloader import Profile
#
# L = instaloader.Instaloader()
# L.login("hadi.notes", "09383890149ppp")        # (login)
# profile = Profile.from_username(L.context, "hadi.notes")
# post = profile.get_posts()
# print(profile)
# print(post)
#
#
# for post in L.get_hashtag_posts("#girl"):
#     t = L.download_post(post, target='#'+"girl")
#
#     print(t)
#

from datetime import datetime
from itertools import dropwhile, takewhile

import instaloader

L = instaloader.Instaloader()
L.login("hadi.notes2", "09383890149ppp1")

posts = instaloader.Profile.from_username(L.context, "hadi.notes").get_posts()

SINCE = datetime(2015, 5, 1)
UNTIL = datetime(2015, 3, 1)

for post in takewhile(lambda p: p.date > UNTIL, dropwhile(lambda p: p.date > SINCE, posts)):
    print(post)
#     L.download_post(post, "instagram")st, "instagram")