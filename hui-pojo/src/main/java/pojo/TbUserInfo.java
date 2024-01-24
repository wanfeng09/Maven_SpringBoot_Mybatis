package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbUserInfo {
    private Integer id;
    private String username;
    private String realname;
    private String gender;
    private Integer age;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
