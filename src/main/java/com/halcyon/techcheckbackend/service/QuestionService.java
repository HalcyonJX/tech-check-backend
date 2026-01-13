package com.halcyon.techcheckbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.techcheckbackend.model.dto.question.QuestionQueryRequest;
import com.halcyon.techcheckbackend.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.halcyon.techcheckbackend.model.vo.QuestionVO;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 张嘉鑫
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2026-01-13 12:56:13
*/
public interface QuestionService extends IService<Question> {
    /**
     * 校验数据
     *
     * @param question
     * @param add      对创建的数据进行校验
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目封装
     *
     * @param question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);

    /**
     * 分页获取题目列表
     *
     * @param questionQueryRequest
     * @return
     */
    Page<Question> listQuestionByPage(QuestionQueryRequest questionQueryRequest);

}
