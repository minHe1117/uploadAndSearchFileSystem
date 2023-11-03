package com.example.uploadfilesystem.CsvConfig;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvConfig {

    @Bean
    public CsvParser csvParser() {
        CsvParserSettings settings = new CsvParserSettings();
        // 设置分隔符为制表符
        settings.getFormat().setDelimiter('\t');
        // 设置每个字段的最大字符数限制，你可以根据需要将其设置得更高
        settings.setMaxCharsPerColumn(5000000); // 例如，这里设置为5000000
        // 其他必要的设置
        settings.setHeaderExtractionEnabled(true);
        settings.setReadInputOnSeparateThread(false);
        settings.setLineSeparatorDetectionEnabled(true);
        settings.setQuoteDetectionEnabled(true);
        settings.setEscapeUnquotedValues(true);

        return new CsvParser(settings);
    }
}
