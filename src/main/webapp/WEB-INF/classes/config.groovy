import java.text.SimpleDateFormat
import java.util.Date

//获取当前日期，在脚本内部使用${fDate}调用，日期格式可自定义，这里默认使用yyyy年MM月dd日
def dateFormat = new SimpleDateFormat("yyyy年MM月dd日")
def fDate = dateFormat.format(new Date())

/*
	1、此配置文件需放置在classpath目录下，此示例为/WEB-INF/classes目录
	
	2、多个map配置文件配置方式：
		map ([idKey],{push [key],[value]})
	
		其中‘map’为groovy方法名，此方法为java脚本引擎生成，暂时须命名为‘map’，若希望使用其他名称，需重构GScript，其中括号可以省略，
		此map为两个参数，第一个参数为map方法key，必须为写死的Integer数据类型，第二个为闭包体，闭包体内可以写java代码或grooy；
		此外还有若干个push方法，每个push方法两个参数，key必须为String，value任意；
	
	3、单个map配置文件配置方式：
		map {push [key],[value]}
		
		单个map同多个map配置区别仅仅是没有idKey，其他配置方式均相同
	
	注意：不能同时有单个map配置和多个map配置
*/

//多个map
map 1,{
	
	push 'title', '我是标题'
	
	//这里可以使用数据库查询
	def sql ="select count(*) from test_table"
	
	push 'msg', "我是内容<br>动态日期：${fDate}<br>".toString()+"我是context map参数："+context.param+"<br>我是注解方法返回值："+getCount(sql)
}
map 2,{
	
	push 'title', '我是标题'
    push 'msg', "我是内容<br>动态日期：${fDate}<br>".toString()+"我是context map参数："+context.param
    
}

/*
//单个map
map {
	
	push 'title', '我是标题'
    
}
*/
