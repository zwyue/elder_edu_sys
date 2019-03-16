package com.zrtjoa.dao;

import com.zrtjoa.entity.Classes;
import com.zrtjoa.entity.Classroom;
import com.zrtjoa.entity.CourseTime;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClassroomDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Classroom record);

    int insertSelective(Classroom record);

    Classroom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);

    /**
     * 查询教室列表
     *
     * @author zwy
     * @date 2018/12/5 11:13
     * @return list
     */
    List<Classroom> queryClassroomList(Classroom classroom);

    /**
     * 查询空闲教室
     *
     * @author zwy
     * @date 2019/2/18 15:16
     * @return list
     * @param map 查询参数
     */
    List<Classroom> queryVacantClsRoom(Map map);

    /**
      * 根据教室名称和教室类别
      * @author yangli
      * @date 2019/2/19
      * @param map
      * @return list
      */
    List<Classroom> getClassroomList(Map map);
}