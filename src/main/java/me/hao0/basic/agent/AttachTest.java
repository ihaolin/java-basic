package me.hao0.basic.agent;

import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.IOException;

/**
 * Author: haolin
 * Date:   7/6/16
 * Email:  haolin.h0@gmail.com
 */
public class AttachTest {

    public static void main(String[] args) throws Exception {

        VirtualMachine vm = VirtualMachine.attach("86738");

        String javaHome = vm.getSystemProperties().getProperty("java.home");

        String agentPath = javaHome + File.separator + "jre" + File.separator + "lib" + File.separator + "management-agent.jar";

        File file = new File(agentPath);

        if(!file.exists()) {
            agentPath = javaHome + File.separator + "lib" + File.separator + "management-agent.jar";
            file = new File(agentPath);
            if(!file.exists())
                throw new IOException("Management agent not found");
        }

        agentPath = file.getCanonicalPath();

        vm.loadAgent(agentPath, "com.sun.management.jmxremote");

        System.out.println(vm.getAgentProperties());

        System.out.println(vm.getSystemProperties());

    }

}
