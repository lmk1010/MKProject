# coding=UTF-8

# @function: 为搜索引擎 为AI核心搜索配置功能
# @useful:
#           keyword      搜索字段
#           searchOrigin 搜索引擎 （searchOrigin：1 百度 2 google
# @return：
#           网页源码（type：100001）
#           解析后的搜索精准结果（type：100002）

from bs4 import BeautifulSoup
from selenium import webdriver
import time

# 配置文件类
search_url = ['https://www.baidu.com/', 'https://www.google.com/']
type = [1000001, 1000002]


def reslove_web_selenium(search_origin, keyword):
    # 加载chrome驱动
    brower = webdriver.Chrome()
    # 设置加载等待最大时间30s 防止加载过慢导致爬虫失败
    brower.implicitly_wait(30)
    # 默认百度搜索
    if search_origin == 1:
        base_url = search_url[0]
    elif search_origin == 2:
        base_url = search_url[1]
    else:
        return '错误！不存在的搜索引擎类型'
    # 对keyword进行判空
    if keyword.strip() == '':
        return 'keyword不能为空!'
    # 开始连接url
    brower.get(base_url)
    # 发送关键字到搜索框
    brower.find_element_by_id('kw').send_keys(keyword)
    brower.find_element_by_id('su').click()

    analysis = BeautifulSoup(brower.page_source, 'lxml')

    # result = analysis.find_all('div', id='container')
    #
    # print(result)
    #    r
    time.sleep(5)
    brower.quit()
    return analysis


if __name__ == '__main__':
    reslove_web_selenium(1, '新白娘子传奇')
