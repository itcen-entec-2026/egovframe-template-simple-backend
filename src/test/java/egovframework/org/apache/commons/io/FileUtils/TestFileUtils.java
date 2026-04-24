package egovframework.org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class TestFileUtils {

	@Test
	void listFiles() {
		if (log.isDebugEnabled()) {
			log.debug("test");
		}

		final File directory = new File("./src/main/java");
		final String[] extensions = { "java" };
		final boolean recursive = true;

		Collection<File> listFiles = FileUtils.listFiles(directory, extensions, recursive);

		if (log.isDebugEnabled()) {
			log.debug("listFiles={}", listFiles);
			log.debug("size={}", listFiles.size());
		}

		StringBuffer sb = new StringBuffer();

		for (File listFile : listFiles) {
			String name = listFile.getName();

			if (log.isDebugEnabled()) {
				log.debug("listFile={}", listFile);
				log.debug("name={}", name);
			}

			if (!name.toLowerCase().endsWith("dao.java")) {
				continue;
			}

			sb.append(System.lineSeparator());
			sb.append(name);
		}

		if (log.isDebugEnabled()) {
			log.debug("sb={}", sb);
		}
	}

}
