//package com.testmonkeys.demo.config;
//
//import com.github.dockerjava.api.DockerClient;
//import com.github.dockerjava.api.command.CreateContainerResponse;
//import com.github.dockerjava.api.model.ExposedPort;
//import com.github.dockerjava.api.model.PortBinding;
//import com.github.dockerjava.core.DockerClientBuilder;
//import com.github.dockerjava.core.command.PullImageResultCallback;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//@Configuration("dockerContainerLifecycleManager")
//public class DockerContainerLifecycleManager implements InitializingBean, DisposableBean {
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        try (final DockerClient dockerClient = DockerClientBuilder.getInstance().build()) {
//            dockerClient.pullImageCmd("mysql:5.7.23")
//                    .exec(new PullImageResultCallback())
//                    .awaitCompletion(30, TimeUnit.SECONDS);
//            final CreateContainerResponse container = dockerClient.createContainerCmd("mysql:5.7.23")
//                    .withName("test-mysql")
//                    .withPortBindings(PortBinding.parse("3307:3306"))
//                    .withEnv("MYSQL_ROOT_PASSWORD=root")
//                    .exec();
//            dockerClient.startContainerCmd(container.getId()).exec();
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Error with initializing docker container", e);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        try (final DockerClient dockerClient = DockerClientBuilder.getInstance().build()) {
//            dockerClient.stopContainerCmd("test-mysql").exec();
//            dockerClient.removeContainerCmd("test-mysql").withRemoveVolumes(true).exec();
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Error with removing docker container", e);
//        }
//    }
//}
