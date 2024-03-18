package com.tanasi.streamflix.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.tanasi.streamflix.adapters.AppAdapter
import com.tanasi.streamflix.utils.format
import com.tanasi.streamflix.utils.toCalendar
import java.util.Calendar

@Entity("episodes")
class Episode(
    @PrimaryKey
    var id: String,
    var number: Int,
    var title: String = "",
    released: String? = null,
    var poster: String? = null,

    var tvShow: TvShow? = null,
    var season: Season? = null,
) : WatchItem, AppAdapter.Item {

    constructor() : this("", 0)

    var released = released?.toCalendar()
    override var isWatched: Boolean = false
    override var watchedDate: Calendar? = null
    @Embedded
    override var watchHistory: WatchItem.WatchHistory? = null


    fun merge(episode: Episode) {
        this.isWatched = episode.isWatched
        this.watchedDate = episode.watchedDate
        this.watchHistory = episode.watchHistory
    }


    @Ignore
    override var itemType = AppAdapter.Type.EPISODE_ITEM

    fun copy(
        id: String = this.id,
        number: Int = this.number,
        title: String = this.title,
        released: String? = this.released?.format("yyyy-MM-dd"),
        poster: String? = this.poster,
        tvShow: TvShow? = this.tvShow,
        season: Season? = this.season,
    ) = Episode(
        id,
        number,
        title,
        released,
        poster,
        tvShow,
        season,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Episode

        if (id != other.id) return false
        if (number != other.number) return false
        if (title != other.title) return false
        if (poster != other.poster) return false
        if (tvShow != other.tvShow) return false
        if (season != other.season) return false
        if (released != other.released) return false
        if (isWatched != other.isWatched) return false
        if (watchedDate != other.watchedDate) return false
        if (watchHistory != other.watchHistory) return false
        return itemType == other.itemType
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + number
        result = 31 * result + title.hashCode()
        result = 31 * result + (poster?.hashCode() ?: 0)
        result = 31 * result + (tvShow?.hashCode() ?: 0)
        result = 31 * result + (season?.hashCode() ?: 0)
        result = 31 * result + (released?.hashCode() ?: 0)
        result = 31 * result + isWatched.hashCode()
        result = 31 * result + (watchedDate?.hashCode() ?: 0)
        result = 31 * result + (watchHistory?.hashCode() ?: 0)
        result = 31 * result + itemType.hashCode()
        return result
    }
}