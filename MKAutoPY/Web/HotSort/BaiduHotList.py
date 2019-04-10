# coding=UTF-8

from bs4 import BeautifulSoup
from selenium import webdriver
import time
import xlwt


def now_hot_list():
    # 打开网页
    url = "http://top.baidu.com/buzz?b=341&c=513&fr=topbuzz_b1_c513"
    driver = webdriver.Chrome()
    driver.get(url)
    # time.sleep(5)

    # 获取网页信息
    html = driver.page_source
    soup = BeautifulSoup(html, 'lxml')

    # 用soup来获得所有'tr'标签
    list = soup.find_all('tr')
    result = []

    # 将所有符合规则的'tr'标签里面的内容提取出来
    for each in list:
        rank = each.find('span')
        key = each.find('a', {'class': 'list-title'})
        point = each.find('td', {'class': 'last'})
        if point != None:
            point = point.find('span')
        if rank != None and key != None and point != None:
            result.append([rank.string, key.string, point.string])

    # 新建xls对象
    workbook = xlwt.Workbook(encoding='utf-8')
    worksheet = workbook.add_sheet('Baidu Rank Data')
    worksheet.write(0, 0, label='rank')
    worksheet.write(0, 1, label='key')
    worksheet.write(0, 2, label='point')

    # 设置列宽
    col = worksheet.col(1)
    col.width = 5000

    # 写入数据
    i = 1
    for each in result:
        rank = str(each[0])
        key = str(each[1])
        point = str(each[2])
        worksheet.write(i, 0, rank)
        worksheet.write(i, 1, key)
        worksheet.write(i, 2, point)
        i += 1

    # 保存
    workbook.save(r'/Users/liumingkang/Downloads/Data.xls')

    print(result)


if __name__ == '__main__':
    now_hot_list()
