package org.zchzh.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.zchzh.music.repository.impl.BaseRepoImpl;

import javax.sql.DataSource;


@EnableJpaRepositories(repositoryBaseClass = BaseRepoImpl.class)
@SpringBootApplication
public class MusicApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MusicApplication.class, args);
		DataSource dataSource = context.getBean(DataSource.class);

		System.out.println(dataSource.getClass());
	}
}
