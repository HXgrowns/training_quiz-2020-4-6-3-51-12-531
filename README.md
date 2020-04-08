# entity.Parking Lot
### 业务逻辑
某大型KTV公司拥有2个停车场，编号为A、B，每个停车场分别可以拥有的停车位为：A: 8, B:10。    
客人来公司后，会将车辆交给公司停车小弟，停车小弟根据如下规则进行停车：   
1. 该公司的停车场均有一位停车小弟操作停取车，停车小弟会按照顺序（先按照停车场编号ASCII顺序选择停车场，再按照数字顺序选择停车位）进行停车。   
2. 如果没有车位，则不能停车。   
3. 停车完毕之后，可以拿到一张有效的停车券，由停车小弟将该券交给客户保存。  

停车小弟会按照下列规则进行取车：       
1. 取车的时候，如果停车券为停车时取到的停车券，则能够取到对应的车，如果停车券无效或已经用过了，则不能取到车。

### 测试用例：  
##### AC0: 当程序启动时，我们会看到如下命令行界面：
      
```
1. 初始化停车场数据
2. 停车
3. 取车
4. 退出
请输入你的选择(1~4)：
```

##### AC1: 输入1，界面显示如下:

```
请输入停车场初始化数据
格式为"停车场编号1:车位数,停车场编号2:车位数" 如 "A:8,B:9"：
```
      
##### AC2: 输入2，界面显示如下:
 
```
请输入车牌号
格式为"车牌号" 如: "A12098"：
```
* 输入车牌号之后，命令行会显示停车券信息：

 ```
 已将您的车牌号为A12098的车辆停到A停车场1号车位，停车券为："A,1,A12098"，请您妥善保存！
 ```

* 如果停车场已满，则程序中断，错误信息为

 ```
 非常抱歉，由于车位已满，暂时无法为您停车！
 ```

##### AC3: 如果我们输入3，那么界面就会变成：

```
请输入停车券信息
格式为"停车场编号,车位编号,车牌号" 如 "A,1,A12098"：
```
 
* 如果输入停车券为停车时拿到的停车券，则命令行输出如下信息   
     
 ```
 已为您取到车牌号为A12098的车辆，很高兴为您服务，祝您生活愉快！
 ```
      
* 如果输入停车券无效（不属于指定的停车场、已经被用过、对应的停车位上没有车、停的是别人的车）则程序中断，输出如下信息：

   ```
      很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！
   ```
     
##### AC4: 如果我们输入4，那么界面输出：

```
系统已退出！
```

### 要求：  
1. 完成代码，使得所有测试运行通过
2. 停车场中停车位的信息使用数据库存储。 
3. 程序初始化时（初始化停车场数据的时候，不是程序启动的时候），需要清空历史遗留数据。 
4. 停车位编号为数字，从1开始依次递增