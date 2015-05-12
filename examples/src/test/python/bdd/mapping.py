host_url = "http://pn.com.ua"

site_mapping = {
    "pc_search": {
        "url": host_url,
        "computers": ".//*[@id='page-content-wrap']/div/div[1]/div/h1[1]/a",
        "notebook": ".//*[@id='page-content-wrap']/div[3]/div[1]/div/div[2]/a",
        "model_number": ".//*[@id='page-content-wrap']/div[3]/div[1]/div[1]/div/div[2]/div[1]/b"
    }
}