package io.legado.test

import io.legado.app.data.entities.Book
import io.legado.app.data.entities.BookSource
import io.legado.app.data.entities.SearchBook
import io.legado.app.data.entities.rule.ExploreKind
import io.legado.app.help.source.exploreKinds
import io.legado.app.model.webBook.BookInfo
import io.legado.app.model.webBook.WebBook

object SourceHelper {
    suspend fun getExploreItems(source: BookSource): List<ExploreKind> {
        return source.exploreKinds()
    }

    suspend fun getExploreBooks(source: BookSource, exploreUrl: String, page:Int? = 1): List<SearchBook>{
        return WebBook.exploreBookAwait(source, exploreUrl, page)
    }

    suspend fun getBookInfo(book: Book, source: BookSource): Book {
        return WebBook.getBookInfoAwait(source, book)
    }
}