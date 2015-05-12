from lettuce import step, world
from hamcrest import *


@step(r'Open "(.*)" page')
def should_open_main_page(step, page):
    world.current_page = world.mapping[page]
    world.browser.get(world.current_page['url'])


@step(u'Click "([^"]*)"')
def click_group1(step, element):
    el = world.browser.find_element_by_xpath(world.current_page[element])
    el.click()


@step(u'See "([^"]*)" in "([^"]*)"')
def see_group1_in_group2(step, text, element):
    el = world.browser.find_element_by_xpath(world.current_page[element])
    assert_that(el.text, equal_to(text))
