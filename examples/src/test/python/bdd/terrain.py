from lettuce import before, after, world
from selenium import webdriver
from mapping import site_mapping


@before.each_feature
def setup(server):
    world.browser = webdriver.Firefox()
    world.browser.implicitly_wait(5)
    world.mapping = site_mapping


@after.all
def tear_down(total):
    # world.browser.close()
    world.browser.quit()