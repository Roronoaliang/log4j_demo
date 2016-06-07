# log4j

��ǩ �����ÿ��

---

## log4j��ʲô������ʲô
����log4j��һ����Դ����־��ܣ�ͨ��log4j�����Կ���**�ض�����**����־��Ϣ��**�ض��ĸ�ʽ**���͵�**�ض����ն�**����ʹ��log4j�ܸ������ͷ���������־��Ϣ���Ӷ��ﵽ����ϵͳ����״���ͼ�¼���������Ŀ�ġ�

## ������Ҫ���
����log4j�м�����Ҫ��������磺loggers,appenders��layouts��levels�ȣ��⼸������ֱ��Ӧ��־��д�롢��־����նˡ���־�Ĳ���ģʽ����־���������

* **Logger**��Logger����־��¼���ĳ��󣬸�����־��д�룬�ڳ�����ͨ�������ö���ʵ�������־��¼�Ĺ��ܡ�log4j��Logger��ϵ�����ж��logger��������ͨ�������Զ�ά�����logger�ļ̳й�ϵ����Logger��ϵ�У���Ψһһ����logger����������logger�����ȣ������ָ����logger���������ô��logger����̳и�logger�����ԡ�

* **Appender**��Appender������ָ����־��Ϣ�����Ŀ�ĵأ�log4j�ṩ�����¼������õ�ʵ���ࣺ

|ʵ����|���Ŀ�ĵ�|
|---|---|---|
|AsyncAppender|�����첽�ķ�ʽ��¼��־
|WriterAppender|����־��Ϣ�����ĸ�ʽ���͵�����ָ���ĵط�|
|ConsoleAppender|����̨|
|FileAppender|�ļ�|
|DailyRollingFileAppender|������ļ���ÿ�춼����һ����־�ļ�|
|RollingFileAppender|������ļ�,�ļ���С�ﵽָ��ֵʱ�����µ���־�ļ�|


* **Layout**:Layout������ָ����־��Ϣ�������ʽ��log4j�ṩ�����¼��ֳ��õĲ��ָ�ʽ��

|ʵ����|����ģʽ|
|---|---|
|HTMLLayout|��HTML�����ʽ����|
|PatternLayout|��ָ���ĸ�ʽ���в��֣����Ը�����ָ������ģʽ|
|SimpleLayout|�򵥲���ģʽ��������־��Ϣ�ļ������Ϣ�ַ���|
|TTCCLayout|������־������ʱ�䡢�̡߳�������Ϣ|

��ʹ��ָ���ĸ�ʽ����ʱ�������ض��ĸ�ʽ������ſ�������ָ�������ʽ��

|����|����|
|---|---|
|%m|���������ָ������Ϣ|
|%M|�������logger�ķ�����|
|%p|������ȼ�|
|%r|������Ӧ�������������log��Ϣ�ķѵĺ�����|
|%c|���logger���ֿռ��ȫ�ƣ�Ҳ���Լ���{����}ָ������Ĳ���|
|%C|�������logger��ȫ����|
|%F|�������logger��Դ�ļ���
|%t|�����������־�̵߳��߳���|
|%n|���һ���س����з�|
|%d|�����־ʱ�������ڻ�ʱ�䣬Ĭ�ϸ�ʽΪISO8601��Ҳ����ָ�������ʽ������:%d{yyyy MM dd HH:mm:ss.SSS}|
|%l|�����־�¼��ķ�����λ�ã������������߳����Լ����ڵĴ��������|
|%x|���̶߳�ջ˳�������־������|
|%X|���߳�ӳ���������־�������ڶ���ͻ�������һ̨�������������������ĸ��ͻ��˲�������־��|

* **Level**��Level��������logger����־����δָ��level��logger���̳�������ĸ�logger����־���𣬸�logger��Ĭ�ϼ�¼������**INFO**��log4j�Դ���8����־���𣬼���ӵ͵��߷ֱ��ǣ�

> 
ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF

* ������Ҫ�����磺**Filter**��������������־��Ϣ�����˵�����Ҫ��¼����־��**LogManager**�������ڹ�����־��ܣ������ϵͳ���������ļ������ж�ȡ��ʼ���ò�����

## ������ʹ��

### log4j��ʹ�ò���

1. ������Ӧ��jar��
2. ��д�����ļ������Բ���properties��xml��ʽ����������ֱ�Ӳ���java�������ã��������顣
3. �ڴ����л�ȡLogger��������������ļ��ŵ�classpath·��������Ҫ��ʾ��ȡ���������ͨ��������������������spring��ȡ�����ļ�������log4j�Ļ�����
```
BasicConfigurator.configure ()�� 
PropertyConfigurator.configure (String configFilename)   
DOMConfigurator.configure ( String filename ) 
```
4. ����logger�������Ӧ������д�벻ͬ�������־��Ϣ���磺

```    
private Logger logger = Logger.getLoger(Test.class);
public void method() {
    logger.info("this is a info information.");
}
```

### properties�����ļ�ʾ��

����log4j��������Ҫ��ָ��logger��level������appender��layout��
����ΪLogger�������Appender������﷨���£�
> 
Log4j.logger.[logger-name] = level, apppender1, appender2...n

����ΪAppender�������layout������﷨���£�
> Log4j.appender.[appender-name].layout = org.apache.log4j.XXLayout 

�������²���properties�����÷�ʽʵ��(���߱�ʵ��Ӧ�òο�����)����������logger�������и�logger���debug���ϼ������־��Ϣ������̨����logger1���INFO����������־��Ϣ���ļ��У���ÿ�����һ�����ļ�����logger2���error����������־��Ϣ���ļ��У�����4KB���½�һ����־�ļ���

```
#���ø�logger,ָ��level��appender
log4j.rootLogger = DEBUG,stout
#ָ��Appender stout������ն����ͺ�layout
log4j.appender.stout = org.apache.log4j.ConsoleAppender
log4j.appender.stout.Target = System.out
log4j.appender.stout.layout = org.apache.log4j.PatternLayout
#ָ������������ʽ
log4j.appender.stout.layout.conversionPattern = %d{yyyy MM dd HH:mm:ss} [Level:%p] %m [Method:%M]%n


#������logger������com.liang.log4j_demo.service���µ�����ʹ��logger����û��ָ���������logger����ʱʹ�ø�logger
log4j.logger.com.liang.log4j_demo.service = INFO,dailyRollingFile
#���ò��Ӹ�logger�̳�appender,Ĭ��Ϊtrue
log4j.additivity.com.liang.log4j_demo.service = FALSE
log4j.appender.dailyRollingFile = org.apache.log4j.DailyRollingFileAppender
#ָ����־�ļ�λ��
log4j.appender.dailyRollingFile.File = logs/log.log
#����appender����ķ�ֵ
#log4j.appender.dailyRollingFile.Threshold = INFO
log4j.appender.dailyRollingFile.layout = org.apache.log4j.PatternLayout
log4j.appender.dailyRollingFile.layout.conversionPattern = [Level:%p] %x%l%m  [Time:%d{yyyy-MM-dd HH:mm:ss.SSS}]%n


##������logger������com.liang.log4j_demo.dao���µ���û��ָ������ʹ�õ���loggerʱʹ�ø�logger
log4j.logger.com.liang.log4j_demo.dao = ERROR,rollingFile
log4j.additivity.com.liang.log4j_demo.dao = FALSE
log4j.appender.rollingFile = org.apache.log4j.RollingFileAppender
log4j.appender.rollingFile.file = logs/error.log
#�����ļ����Ϊ4KB,������ֵ��ع���־�ļ�
log4j.appender.rollingFile.MaxFileSize = 4KB
#����ļ�������Ϊ2
log4j.appender.file.maxBackupIndex=2
log4j.appender.rollingFile.layout = org.apache.log4j.PatternLayout
log4j.appender.rollingFile.layout.conversionPattern = %n%n%d{yyyy-MM-dd HH:mm:ss}  [Level: %p]  [Position: %c]  [Number: %L]%n%n%m
```

### FileAppender��RollingFileAppender��DailyRollingFileAppender����
**FileAppender����������־��¼���ļ���**��RollingFileAppender��DailyRollingFileAppender���������࣬FileAppender���е��������£�
|����|˵��|
|---|---|
|immediateFlush|Ĭ��Ϊtrue������ÿ����־׷�Ӳ������������ˢ�µ��ļ���|
|encoding|ָ�����룬Ĭ��ʹ��ƽ̨��صı���|
|threshold|appender�������õķ�ֵ��log���̵ĵ�һ�����Ǽ��ü���|
|Filename|��־�ļ���|
|fileAppend|Ĭ����true����ʾ����־��Ϣ׷�ӵ��ļ�ĩβ|
|bufferedIO|Ĭ��Ϊfalse����ʾ�Ƿ�򿪻�����|
|bufferSize|��������bufferedIOΪtrueʱ����Ч��Ĭ�ϻ�������СΪ8kb|

**RollingFileAppender������������־д�����ļ�**�������ļ��Ĵ�С�ﵽһ����ֵ�ǻع���������FileAppender��ͬ�������⣬RollingFileAppender��ӵ���������ԣ�

|����|˵��|
|---|---|
|maxFileSize|����ļ���С������־�ļ������趨��ֵʱ�����ع���Ĭ��Ϊ10MB|
|maxBackupIndex|��ʾ�����ļ��ĸ�����Ĭ��Ϊ1|

DailyRollingFileAppender��������ÿ�������µ���־�ļ������˼̳�FileAppender�����������⣬��������һ��DatePattern���ԣ���������ʲôʱ��ع��ļ���Ĭ���������ÿ�����ҹ�ع��ļ���

|DatePattern|˵��|
|---|---|
|'.'yyyy-MM|�ڱ���ĩ�����³��ع��ļ�|
|'.'yyyy-MM-dd|��ÿ����ҹ�ع��ļ���Ĭ��ֵ|
|'.'yyyy-MM-dd-a|��ÿ���������ҹ�ع��ļ�|
|'.'yyyy-MM-dd-HH|��ÿ������ع��ļ�|
|'.'yyyy-MM-dd-HH-mm|��ÿ���ӻع��ļ�|
|'.'yyyy-ww|���ݵ�����ÿ�ܵĵ�һ��ع��ļ�|

## log4j�Ĺ�������

1. **Threshold���**����������logger.debug()�ȷ�����Log4j��ʹ�õ�ǰ��logger�������ڵ���ϵ���������õ��ż�ȥ�����־�ļ���������𲻹������ϴ˴���־����
2. **Level���**��ʹ�õ�ǰLogger�����û��߼̳еõ���Level��������־����������𲻹������ϴ˴���־����
3. **����LoggingEvent����**������������ͨ�����򴴽�һ����־�¼�����LoggingEvent���ö����װ�˱�����־������ز�����Ϣ������־���ݣ���־ʱ��ȡ�
4. **ִ��Appender**����LoggingEvent���󽻸���ǰLogger���������õ�Appender���󣬲���������appender�����ϵ�doAppend()����������־��Ϣ��
5. **��ʽ����־��Ϣ**��ʹ��ÿһ��Append����󶨵�Layout��������ʽ����־��Ϣ��
6. **�����־��Ϣ**��ͨ����ʽ����õ���String�����ɸ�appender�����������ָ�����ն��ϣ��������̨�����ļ���
����
![](http://ww1.sinaimg.cn/large/0065HC85gw1f4n1dn0m8dj30ex08ewey.jpg)

## ��־��¼������

> 
1. ��¼����Ϣ�����Ǻ�����ȷ���������
2. �Ƽ���¼ҵ��������Ϣ����ҵ��ʼ��ҵ�����̽���ʱ��ӡ���պͷ��ͳ�ȥ����Ϣ���Ͻ����ڲ�������δ�ӡ��Ϣ����
3. �Ƽ���¼�����Ĺؼ��������ؼ����ݽṹ
4. �Ƽ���¼����ҵ�������쳣ջ�ռ�
5. ���Ƽ���¼��������� ������
6. ���Ƽ���¼�к�%L��ȫ����%C��Դ�ļ���%F��������%M��λ����Ϣ(���۸�)


