package com.example.potpot.domain.apply.service

import com.example.potpot.domain.apply.domain.Apply
import com.example.potpot.domain.apply.domain.ApplyRepository
import com.example.potpot.domain.apply.exception.AlreadyApplyException
import com.example.potpot.domain.apply.exception.NotAllowSelfApplicationException
import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.exception.FeedNotFoundException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApplyService(
    private val userFacade: UserFacade,
    private val applyRepository: ApplyRepository,
    private val feedRepository: FeedRepository
) {
    @Transactional
    fun execute(request: Long) {
        val user = userFacade.getCurrentUser()
        val feed = feedRepository.findById(request) ?: throw FeedNotFoundException

        if (feed.user.schoolNumber == user.schoolNumber) throw NotAllowSelfApplicationException
        if (applyRepository.existsByUserAndFeed(user, feed)) throw AlreadyApplyException

        applyRepository.save(
            Apply(
                user = user,
                feed = feed
            )
        )
    }
}
