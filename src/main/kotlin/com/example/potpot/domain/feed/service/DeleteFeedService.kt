package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.exception.FeedNotFoundException
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteFeedService(
    private val feedRepository: FeedRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(id: Long) {
        val user = userFacade.getCurrentUser()
        val feed = feedRepository.findById(id).orElseThrow() ?: throw FeedNotFoundException

        if (user.id != feed.user.id) throw UserMismatchException

        feedRepository.delete(feed)
    }
}
