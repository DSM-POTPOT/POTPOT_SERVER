package com.example.potpot.domain.apply.service

import com.example.potpot.domain.apply.domain.ApplyRepository
import com.example.potpot.domain.apply.exception.ApplyNotFoundException
import com.example.potpot.domain.apply.presentation.dto.request.ChangeStatusRequest
import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.exception.FeedNotFoundException
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChangeIsOKService(
    private val userFacade: UserFacade,
    private val applyRepository: ApplyRepository,
    private val feedRepository: FeedRepository
) {

    @Transactional
    fun execute(request: ChangeStatusRequest) {
        val user = userFacade.getCurrentUser()
        val apply = applyRepository.findByIdOrNull(request.id) ?: throw ApplyNotFoundException
        val feed = feedRepository.findByIdOrNull(apply.feed.id) ?: throw FeedNotFoundException

        if (user.schoolNumber != feed.user.schoolNumber) throw UserMismatchException

        apply.changeStatus()
    }
}
