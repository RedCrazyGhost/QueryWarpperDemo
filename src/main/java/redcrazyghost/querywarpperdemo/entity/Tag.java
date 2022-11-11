package redcrazyghost.querywarpperdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author RedCrazyGhost - wenxingzhan
 * @date 2022/11/03 12:44
 **/
@Data
@NoArgsConstructor
@TableName(value="tags",autoResultMap = true)
public class Tag implements Serializable {

    /**
     * 标签ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名字
     */
    private String name;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 文章ID列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> noteIdList;

    /**
     * 是否逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1234561241L;

    public Tag(String name){
        this.name=name;
    }
}
