package cn.com.agree.aweb.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author zhongyi@agree.com.cn
 */
public class JpaFreemarkerTemplateResolver {

	private static final Map<String, Template> MAP = new ConcurrentHashMap<>();
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private List<String> suffix = Arrays.asList("sftl");
	private List<String> basePath = Arrays.asList("classpath*:query-templates/**");
	private Boolean enableNativeQuery = false;

	public List<String> getSuffix() {
		return suffix;
	}
	public void setSuffix(List<String> suffix) {
		this.suffix = suffix;
	}
	public List<String> getBasePath() {
		return basePath;
	}
	public void setBasePath(List<String> basePath) {
		this.basePath = basePath;
	}
	public boolean isEnableNativeQuery() {
		return enableNativeQuery;
	}
	public void setEnableNativeQuery(boolean enableNativeQuery) {
		this.enableNativeQuery = enableNativeQuery;
	}

	public JpaFreemarkerTemplateResolver() {
	}

	public JpaFreemarkerTemplateResolver(List<String> suffix, List<String> basePath, boolean enableNativeQuery) {
		super();
		this.suffix = suffix;
		this.basePath = basePath;
		this.enableNativeQuery = enableNativeQuery;
	}

	public void init() {
		Configuration configuration = new Configuration(new Version("2.3.0"));
		configuration.setNumberFormat("#");
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		basePath.parallelStream().map(t -> {
			try {
				return resolver.getResources(t);
			} catch (IOException e2) {
				log.error("", e2);
				return new Resource[] {};
			}
		}).map(Arrays::asList).flatMap(List::stream).filter(item -> item != null && suffix.stream().anyMatch(suf -> Objects.requireNonNull(item.getFilename()).endsWith(suf))).forEach(resource -> {
			try (InputStream is = resource.getInputStream()){
				log.info("reading template file {}", resource.getFilename());
				byte[] bytes = IOUtils.toByteArray(is);
				Stream.of(new String(bytes, StandardCharsets.UTF_8).split("@---")).filter(item -> item != null && !item.trim().isEmpty()).forEach(item -> {
					String key = item.substring(0, item.indexOf("\n")).trim();
					String content = item.substring(item.indexOf("\n") + 1);
					try {
						MAP.put(key, new Template(key, new StringReader(content), configuration));
						log.info("{} query template loaded", key);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}catch (Exception e) {
				log.error("", e);
			}
		});
	}
	
	public static String getQueryString(String key, Object params) throws TemplateException, IOException {
		try(Writer writer = new StringWriter()){
			MAP.get(key).process(params, writer);
			return writer.toString();
		}
	}
	
}
