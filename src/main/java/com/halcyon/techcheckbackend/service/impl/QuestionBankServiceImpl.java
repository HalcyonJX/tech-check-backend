package com.halcyon.techcheckbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.techcheckbackend.model.entity.QuestionBank;
import com.halcyon.techcheckbackend.service.QuestionBankService;
import com.halcyon.techcheckbackend.mapper.QuestionBankMapper;
import org.springframework.stereotype.Service;

/**
* @author 张嘉鑫
* @description 针对表【question_bank(题库)】的数据库操作Service实现
* @createDate 2026-01-13 12:57:17
*/
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank>
    implements QuestionBankService{

}




