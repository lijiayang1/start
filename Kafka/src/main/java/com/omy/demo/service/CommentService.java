package com.omy.demo.service;

import com.omy.demo.dao.CommentDao;
import com.omy.demo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lijiayang
 * @date: 2019-01-23 10:43
 * @description:
 */

@Service
public class CommentService {
    @Autowired
    public CommentDao commentDao;

    public List<Comment> getAllComment(){
        return commentDao.getCommentAll();
    }
}
