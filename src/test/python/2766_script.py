__author__ = 'stde'
# http://code.google.com/p/selenium/issues/detail?id=2766

from selenium import webdriver
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities


def main():
    driver = webdriver.Firefox()
    driver.get("http://www.freelancersunion.org/about/index.html")

    link = driver.find_element_by_css_selector('a[href="#y1990s"]')
    link.click()


if __name__ == "__main__":
    main()

