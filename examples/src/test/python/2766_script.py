# http://code.google.com/p/selenium/issues/detail?id=2766

from selenium import webdriver


def main():
    driver = webdriver.Firefox()

    driver.get("http://www.freelancersunion.org/about/index.html")

    link = driver.find_element_by_css_selector('a[href="#y1990s"]')
    link.click()
    driver.quit()


if __name__ == "__main__":
    main()

