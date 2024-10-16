package com.example.potpot_server.domain.feed.domain

import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository : JpaRepository<Feed, Long>
