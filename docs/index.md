tip: 并行调用多次读写接口存在读写失败的可能性。
tip: 小程序不会对写入数据包大小做限制，但系统与蓝牙设备会确定蓝牙4.0单次传输的数据大小，超过最大字节数后会发生写入错误，建议每次写入不超过20字节。
tip: 安卓平台上，在调用notify成功后立即调用write接口，在部分机型上会发生 10008 系统错误
bug: 若单次写入数据过长，iOS平台上存在系统不会有任何回调的情况(包括错误回调)。