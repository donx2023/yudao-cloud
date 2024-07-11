import os
import re

# 定义Java类型到MySQL类型的映射
type_mapping = {
    'String': 'VARCHAR(255)',
    'Integer': 'INT',
    'Long': 'BIGINT',
    'double': 'DOUBLE',
    'float': 'FLOAT',
    'boolean': 'BOOLEAN',
    'Date': 'DATETIME',
    'BigDecimal': 'DECIMAL(10,2)',
}

# 将驼峰命名转换为下划线分隔
def camel_to_snake(name):
    s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', name)
    return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1).lower()

# 解析Java实体类文件
def parse_java_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read()
    patternWithVal = r'@TableName\(value\s*=\s*"([^"]+)".*\)'
    patternNoVal = r'@TableName\("([^"]+)".*\)'
    matchVal = re.search(patternWithVal, content)
    matchNoVal = re.search(patternNoVal, content)
    if matchVal:
        class_name = matchVal.group(1)
    elif matchNoVal:
        class_name = matchNoVal.group(1)
    else:
        class_name = None



    # 获取字段
    fields = re.findall(r'private\s+(\w+)\s+(\w+);', content)

    return class_name, fields

# 生成CREATE TABLE SQL语句
def generate_create_table_sql(class_name, fields):
    # table_name = camel_to_snake(class_name)
    table_name = class_name
    sql = f"DROP TABLE IF EXISTS {table_name}; \nCREATE TABLE {table_name} (\n"
    first_field = True

    for field_type, field_name in fields:
        mysql_type = type_mapping.get(field_type, 'VARCHAR(255)')
        field_name_snake = camel_to_snake(field_name)
        if first_field:
            sql += f"  {field_name_snake} {mysql_type} AUTO_INCREMENT PRIMARY KEY,\n"
            first_field = False
        else:
            sql += f"  {field_name_snake} {mysql_type},\n"
    sql += "  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n"
    sql += "  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
    sql += "  creator VARCHAR(255),\n"
    sql += "  updater VARCHAR(255),\n"
    sql += "  deleted TINYINT(1) DEFAULT 0,\n"
    sql += "  tenant_id BIGINT,\n"
    sql = sql.rstrip(",\n") + "\n);"
    return sql

# 主函数
def read_files_and_subdirectories(directory, output_file):
    with open(output_file, 'w', encoding='utf-8') as out_file:
        for root, dirs, files in os.walk(directory):
            for file_name in files:
                if file_name.endswith('.java'):
                    file_path = os.path.join(root, file_name)
                    print(file_path)
                    class_name, fields = parse_java_file(file_path)
                    create_table_sql = generate_create_table_sql(class_name, fields)
                    out_file.write(create_table_sql + '\n\n')


if __name__ == '__main__':
    directory = '/Users/xiedong/develop/donx/yudao-cloud-donx/yudao-module-erp/yudao-module-erp-biz/src/main/java/cn/iocoder/yudao/module/erp/dal/dataobject'  # 替换为你的Java实体类目录路径
    output_file = 'erp.sql'  # 生成的SQL脚本文件名
    read_files_and_subdirectories(directory, output_file)
