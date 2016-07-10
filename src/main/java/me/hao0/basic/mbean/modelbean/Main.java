package me.hao0.basic.mbean.modelbean;

import javax.management.*;
import javax.management.modelmbean.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // create a MBeanServer
        MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer();

        // create a RequiredModelMBean for managing the server
        RequiredModelMBean serverMBean =
                (RequiredModelMBean) mBeanServer.instantiate(
                        "javax.management.modelmbean.RequiredModelMBean");

        // create an ObjectName for this server
        ObjectName serverMBeanName = new ObjectName("server: id=Server");
        serverMBean.setModelMBeanInfo(getModelMBeanInfoForServer(serverMBeanName));
        Server server = new Server();
        serverMBean.setManagedResource(server, "ObjectReference");

        ObjectInstance registeredServerMBean =
                mBeanServer.registerMBean(serverMBean, serverMBeanName);

        serverMBean.invoke("start", null, null);

        Thread.sleep(1000);

        System.out.println(serverMBean.getAttribute("upTime"));
        Thread.sleep(5000);
        System.out.println(serverMBean.getAttribute("upTime"));
    }

    private static ModelMBeanInfo getModelMBeanInfoForServer(ObjectName objectName)
            throws Exception {
        ModelMBeanAttributeInfo[] serverAttributes = new ModelMBeanAttributeInfo[1];

        // upTime属性描述
        Descriptor upTime = new DescriptorSupport(
                new String[]{
                        "name=upTime",
                        "descriptorType=attribute",
                        "displayName=Server upTime",
                        "getMethod=getUpTime",  // 获取upTime属性时, 委托给getUpTimeDesc操作
                        "currencyTimeLimit=-1"});
        // MBean属性
        serverAttributes[0] =
                new ModelMBeanAttributeInfo("upTime", "long", "Server upTime", true, false, false, upTime);

        // MBean方法信息
        ModelMBeanOperationInfo[] serverOperations = new ModelMBeanOperationInfo[2];

        // getUpTime方法
        Descriptor getUpTimeDesc = new DescriptorSupport(
                new String[]{
                    "name=getUpTime",
                    "descriptorType=operation",
                    "class=Server",
                    "role=operation",
                    "currencyTimeLimit=-1"});
        MBeanParameterInfo[] getUpTimeParms = new MBeanParameterInfo[0];
        serverOperations[0] = new ModelMBeanOperationInfo("getUpTime",
                "get the up time of the server", getUpTimeParms, "java.lang.Long", MBeanOperationInfo.ACTION, getUpTimeDesc);

        // start方法
        Descriptor startDesc =
                new DescriptorSupport(
                        new String[]{
                                "name=start",
                                "descriptorType=operation",
                                "class=Server",
                                "role=operation"
                        });
        MBeanParameterInfo[] startParms = new MBeanParameterInfo[0];
        serverOperations[1] = new ModelMBeanOperationInfo("start",
                "start(): start server",
                startParms,
                "java.lang.Integer",
                MBeanOperationInfo.ACTION,
                startDesc);

        ModelMBeanInfo serverMMBeanInfo =
                new ModelMBeanInfoSupport(
                        "Server",
                        "ModelMBean for managing an Server",
                        serverAttributes,
                        null,
                        serverOperations,
                        null);

        //Default strategy for the MBean.
        Descriptor serverDescription =
                new DescriptorSupport(
                        new String[]{
                                ("name=" + objectName),
                                "descriptorType=mbean",
                                ("displayName=Server"),
                                "type=Server",
                                "log=T",
                                "logFile=serverMX.log",
                                "currencyTimeLimit=10"}); //currencyTimeLimit=10缓存属性10s
        serverMMBeanInfo.setMBeanDescriptor(serverDescription);
        return serverMMBeanInfo;
    }
}