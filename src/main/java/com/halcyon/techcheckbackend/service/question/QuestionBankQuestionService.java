package com.halcyon.techcheckbackend.service.question;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.halcyon.techcheckbackend.model.dto.questionBankQuestion.QuestionBankQuestionQueryRequest;
import com.halcyon.techcheckbackend.model.entity.QuestionBankQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.halcyon.techcheckbackend.model.vo.QuestionBankQuestionVO;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 张嘉鑫
* @description 针对表【question_bank_question(题库题目)】的数据库操作Service
* @createDate 2026-01-13 12:57:24
*/
public interface QuestionBankQuestionService extends IService<QuestionBankQuestion> {
    /**
     * 校验数据
     *
     * @param questionBankQuestion
     * @param add 对创建的数据进行校验
     */
    void validQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionBankQuestionQueryRequest
     * @return
     */
    QueryWrapper<QuestionBankQuestion> getQueryWrapper(QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest);

    /**
     * 获取题库题目关联封装
     *
     * @param questionBankQuestion
     * @param request
     * @return
     */
    QuestionBankQuestionVO getQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion, HttpServletRequest request);

    /**
     * 分页获取题库题目关联封装
     *
     * @param questionBankQuestionPage
     * @param request
     * @return
     */
    Page<QuestionBankQuestionVO> getQuestionBankQuestionVOPage(Page<QuestionBankQuestion> questionBankQuestionPage, HttpServletRequest request);
}
