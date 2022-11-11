package redcrazyghost.querywarpperdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redcrazyghost.querywarpperdemo.entity.Tag;
import redcrazyghost.querywarpperdemo.mapper.TagMapper;

import javax.annotation.Resource;

/**
 * @author RedCrazyGhost - wenxingzhan
 * @date 2022/11/03 12:10
 **/
@SpringBootTest
public class QueryWarpperTest {

    @Resource
    private TagMapper tagMapper;

    @Test
    public void test(){
        QueryWrapper<Tag> tagQueryWrapper=new QueryWrapper<Tag>();
        tagQueryWrapper.eq("name","Java");
        System.out.println(tagMapper.selectList(tagQueryWrapper));
    }
}
