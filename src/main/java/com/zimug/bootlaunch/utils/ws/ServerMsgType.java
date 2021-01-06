package com.zimug.bootlaunch.utils.ws;

/**
 * The type ServerMsgType.
 *
 * Created on 2018/12/11
 *
 * @author ChaoHong.Mao
 */
public enum ServerMsgType {
    // 云环境
    CLOUD_ENV("云环境", "cloud_env"),
    // 分区
    RES_POOL("分区", "res_pool"),
    // 实例
    VM("实例", "vm"),
    //镜像
    IMAGE("镜像", "image"),
    // 硬盘
    VD("硬盘", "vd"),
    // 硬盘备份
    VDBACKUP("硬盘备份", "vdbackup"),
    // 网络
    VPC("私有网络", "network"),
    VPC_PORT("虚拟网卡", "vpc_port"),
    // 子网
    SUBNET("子网", "network"),
    // 弹性IP
    FLOATING_IP("弹性IP", "floating_ip"),
    // 带宽
    BANDWIDTH("带宽", "floating_ip"),
    // 安全组
    SECURITY_GROUP("安全组", "security_group"),
    // RDS
    RDS("RDS", "rds"),
    // RDS外网地址
    RDS_CONNECT_STRING("RDS连接字符串", "rds"),
    // RDS 白名单
    RDS_IP_ARRAY("RDS白名单", "rds"),
    // RDS 账户列表
    RDS_ACCOUNT("RDS账户列表", "rds"),
    // 路由器
    ROUTER("路由器", "router"),
    // 路由器接口
    ROUTER_INTERFACE("路由器接口", "router"),
    // 路由表条目
    ROUTER_ROUTE("路由表条目", "router"),
    // 快照
    SNAPSHOT("快照", "snapshot"),
    // 负载均衡
    RES_SLB("负载均衡", "slb"),
    // 负载均衡监听
    RES_SLB_LISTENER("负载均衡监听", "slb"),
    // 负载均衡后端服务器
    RES_SLB_BACKEND("负载均衡后端服务器", "slb"),
    // 负载均衡后端服务器组
    RES_SLB_BACKEND_GROUP("负载均衡后端服务器组", "slb"),
    // 告警
    ALARM("告警", "alarm"),
    // 任务
    TASK("任务", "task"),
    // 弹性文件
    SHARE("弹性文件", "share"),
    SHARE_GROUP("弹性文件权限组", "share_group"),
    SHARE_MOUNT_TARGET("弹性文件挂载点", "share_mount_target"),
    GATEWAY("云管网关", "gateway"),
    RCLINK_GATEWAY("二级云管网关", "rclink_gateway"),
    RCLINK_GATEWAY_ENV("二级云管网关底层", "rclink_gateway_env"),
    // 服务链
    SERVICE_CHAIN("服务链", "service_chain"),
    // MQ实例
    MQ_INSTANCE("MQ实例", "mq_instance"),
    // 服务链特征组
    SERVICE_CHAIN_CONTEXT("流量特征组", "service_chain_context"),
    DCS("高速缓存实例", "dcs"),
    DCS_CONFIG("高速缓存实例配置参数", "dcs_config"),
    //对象存储
    OBS_SYNC("同步对象", "obs"),
    // 资源池
    MACHINE("资源池", "machine");
    String type;

    String typeFamily;

    ServerMsgType(String type, String typeFamily) {
        this.type = type;
        this.typeFamily = typeFamily;
    }

    public String getType() {
        return type;
    }

    public String getTypeFamily() {
        return typeFamily;
    }

}
