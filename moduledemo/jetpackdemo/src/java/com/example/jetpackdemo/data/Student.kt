package java.com.example.jetpackdemo.data

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

/**
 * @Entity ：表名
 * @PrimaryKey ：主键 autoGenerate是否自增长
 * @ColumnInfo ：列名和列类型
 * @Ignore ：忽略该字段(数据库默认有一个构造方法，使用@Ignore忽略其他两个构造方法)
 *      ex:Room cannot pick a constructor since multiple constructors are suitable. Try to annotate unwanted constructors with @Ignore.
 */

@Entity(tableName = "student")
class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id = 0

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name: String? = null

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    var age = 0

    @ColumnInfo(name = "sex", typeAffinity = ColumnInfo.TEXT)
    var sex: String? = null

    @ColumnInfo(name = "grade", typeAffinity = ColumnInfo.INTEGER)
    var grade = 0

    constructor(id: Int, name: String?, age: Int) {
        this.id = id
        this.name = name
        this.age = age
    }

    @Ignore
    constructor(name: String?, age: Int) {
        this.name = name
        this.age = age
    }

    @Ignore
    constructor(id: Int) {
        this.id = id
    }
}