package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLogAOP {
    private Integer id;
    private Integer operateUserId;
    private LocalDateTime operateTime;
    private String className;
    private String methodName;
    private String MethodParams;
    private String returnValue;
    private Long costTime;
}
