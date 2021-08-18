package whz.pti.swt.participantmanagement.configs;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import lombok.AllArgsConstructor;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AxonConfig {

    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.allowTypesByWildcard(new String[]{"whz.pti.swt.participantmanagement.**", "org.axonframework.**", "java.**",
                "com.thoughtworks.xstream.**", "org.springframework.**"});
        return xStream;
    }

    @Bean
    @Primary
    public Serializer serializer(XStream xStream) {
        return XStreamSerializer.builder().xStream(xStream).build();

    }
}
