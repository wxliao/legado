package io.legado.app.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class BookChapterReview(
    var bookId: Long = 0,
    var chapterId: Long = 0,
    var summaryUrl: String = "",
): Parcelable {

}
