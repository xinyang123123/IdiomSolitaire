import sqlite3

# 打开文本数据
data = open("data.txt", "r")
# 连接数据库
db = sqlite3.connect("/Volumes/develop/Projects/db/idiom.db")
# 获取游标
cursor = db.cursor()

# 读取文本数据
while True:
    linData = data.readline()
    # 判断是否最后一行
    if len(linData):
        db.close()
        print('done')
        break
    # 使用空格切割字符串
    strArray = linData.split()
    idiom = strArray[0]
    pinyin = strArray[1]
    desc = strArray[2]
    # 获取单个拼音数组
    chars = pinyin.split("\'")

    # 不要使用%s % value 之类的字符串替换，会造成sql注入攻击 如：PINYIN = zhu'ju'zhu'zi
    # https://stackoverflow.com/questions/50660505/what-does-sqlite3-operationalerror-near-t-syntax-error-means
    sql = 'INSERT into idiom_info (IDIOM,PINYIN,DESCRIPTION,FIRST_PINYIN,LAST_PINYIN) values (?,?,?,?,?)'

    cursor.execute(sql, [idiom, pinyin, desc, chars[0], chars[len(chars) - 1]])
    db.commit()
