package cn.webdav.pojo.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "客户端选择的prop的实体类")
public class SelectedProp {

    private boolean displayname;

    private boolean getcontentlength;

    private boolean author;

    private boolean creationdate;

    private boolean getcontentlanguage;

    private boolean getcontenttype;

    private boolean getetag;

    private boolean getlastmodified;

    public static SelectedProp allTrue() {
        return SelectedProp.builder()
                .displayname(true)
                .getcontentlength(true)
                .author(true)
                .creationdate(true)
                .getcontentlanguage(true)
                .getcontenttype(true)
                .getetag(true)
                .getlastmodified(true)
                .build();
    }
}
