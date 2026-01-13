package com.halcyon.techcheckbackend.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.techcheckbackend.constant.SortConstant;
import com.halcyon.techcheckbackend.exception.ErrorCode;
import com.halcyon.techcheckbackend.exception.ThrowUtils;
import com.halcyon.techcheckbackend.model.dto.question.QuestionQueryRequest;
import com.halcyon.techcheckbackend.model.entity.Question;
import com.halcyon.techcheckbackend.model.entity.User;
import com.halcyon.techcheckbackend.model.vo.QuestionVO;
import com.halcyon.techcheckbackend.model.vo.UserVO;
import com.halcyon.techcheckbackend.service.QuestionService;
import com.halcyon.techcheckbackend.mapper.QuestionMapper;
import com.halcyon.techcheckbackend.service.UserService;
import com.halcyon.techcheckbackend.utils.SqlUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 张嘉鑫
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2026-01-13 12:56:13
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    @Resource
    private UserService userService;

    @Override
    public void validQuestion(Question question, boolean add) {
        //校验对象
        ThrowUtils.throwIf(question == null, ErrorCode.PARAMS_ERROR, "题目不能为空");
        //从对象取值
        String title = question.getTitle();
        String content = question.getContent();
        //如果是创建，标题和内容不能为空
        if (add) {
            ThrowUtils.throwIf(title == null || title.isEmpty(), ErrorCode.PARAMS_ERROR, "标题不能为空");
            ThrowUtils.throwIf(content == null || content.isEmpty(), ErrorCode.PARAMS_ERROR, "内容不能为空");
        }
        //校验长度
        if (StringUtils.isNotBlank(title)) {
            ThrowUtils.throwIf(title.length() > 80, ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(content)) {
            ThrowUtils.throwIf(content.length() > 10240, ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }

    @Override
    public QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest) {
        ThrowUtils.throwIf(questionQueryRequest == null, ErrorCode.PARAMS_ERROR, "查询参数不能为空");
        Long id = questionQueryRequest.getId();
        Long notId = questionQueryRequest.getNotId();
        String title = questionQueryRequest.getTitle();
        String content = questionQueryRequest.getContent();
        String searchText = questionQueryRequest.getSearchText();
        String sortField = questionQueryRequest.getSortField();
        String sortOrder = questionQueryRequest.getSortOrder();
        List<String> tagList = questionQueryRequest.getTags();
        Long userId = questionQueryRequest.getUserId();
        String answer = questionQueryRequest.getAnswer();
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchText)) {
            // 需要拼接查询条件
            queryWrapper.and(qw -> qw.like("title", searchText).or().like("content", searchText));
        }
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
        queryWrapper.like(StringUtils.isNotBlank(answer), "answer", answer);
        if (CollUtil.isNotEmpty(tagList)) {
            for (String tag : tagList) {
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }
        queryWrapper.ne(ObjectUtils.isNotEmpty(notId), "id", notId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(SortConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public QuestionVO getQuestionVO(Question question, HttpServletRequest request) {
        QuestionVO questionVO = QuestionVO.objToVo(question);
        Long userId = question.getUserId();
        User user = null;
        if (userId != null && userId > 0) {
            user = userService.getById(userId);
        }
        UserVO userVO = userService.getUserVO(user);
        questionVO.setUser(userVO);
        if (user != null) {
            questionVO.setUser(userService.getUserVO(user));
        }
        return questionVO;
    }

    @Override
    public Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request) {
        //todo
        return null;
    }

    @Override
    public Page<Question> listQuestionByPage(QuestionQueryRequest questionQueryRequest) {
        //todo
        return null;
    }
}




