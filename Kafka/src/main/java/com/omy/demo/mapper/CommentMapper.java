package com.omy.demo.mapper;

import com.omy.demo.model.Comment;
import com.omy.demo.model.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CommentMapper {
    @SelectProvider(type=CommentSqlProvider.class, method="countByExample")
    int countByExample(CommentExample example);

    @DeleteProvider(type=CommentSqlProvider.class, method="deleteByExample")
    int deleteByExample(CommentExample example);

    @Delete({
        "delete from comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into comment (id, comment)",
        "values (#{id,jdbcType=INTEGER}, #{comment,jdbcType=CHAR})"
    })
    int insert(Comment record);

    @InsertProvider(type=CommentSqlProvider.class, method="insertSelective")
    int insertSelective(Comment record);

    @SelectProvider(type=CommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="comment", property="comment", jdbcType=JdbcType.CHAR)
    })
    List<Comment> selectByExample(CommentExample example);

    @Select({
            "select",
            "id, comment",
            "from comment",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="comment", property="comment", jdbcType=JdbcType.CHAR)
    })
    Comment selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "*",
            "from comment"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="comment", property="comment", jdbcType=JdbcType.CHAR)
    })
    List<Comment> selectGetAll();

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Comment record);

    @Update({
        "update comment",
        "set comment = #{comment,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comment record);
}