package com.omy.demo.dao;

import com.omy.demo.mapper.CommentMapper;
import com.omy.demo.model.Comment;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lijiayang
 * @date: 2019-01-22 15:36
 * @description:
 */
@Transactional
@Component
public class CommentDao {

    public static  SqlSession sqlSession;

    public List<Comment> getCommentAll(){
        sqlSession = utils.getSession();
        List<Comment> list = sqlSession.getMapper(CommentMapper.class).selectGetAll();
        utils.closeSession(sqlSession);
        return list;
    }

}
