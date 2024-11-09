package com.example.potpot.domain.apply.service

import com.example.potpot.domain.apply.domain.ApplyRepository
import com.example.potpot.domain.apply.exception.ApplyNotFoundException
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteApplyService(
    private val userFacade: UserFacade,
    private val applyRepository: ApplyRepository
) {

    @Transactional
    fun execute(feedId: Long) {
        val user = userFacade.getCurrentUser()
        val apply = applyRepository.findByFeedIdAndUserId(feedId, user.id) ?: throw ApplyNotFoundException

        if (user.schoolNumber != apply.user.schoolNumber) throw UserMismatchException
        applyRepository.delete(apply)
    }
}
