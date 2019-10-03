import sqlite3

# 连接数据库
db = sqlite3.connect("/Volumes/develop/Projects/db/idiom.db")
# 获取游标
cursor = db.cursor()

# 统计每个字母开头有多少成语
cursor.execute("SELECT FIRST_PINYIN,COUNT(*) FROM idiom_info GROUP BY FIRST_PINYIN")
# 获取查询结果
results = cursor.fetchall()

for row in results:
    sql = 'INSERT into idiom_count (pinyin, COUNT) values (?,?)'
    cursor.execute(sql, [row[0], row[1]])

db.commit()

db.close()
