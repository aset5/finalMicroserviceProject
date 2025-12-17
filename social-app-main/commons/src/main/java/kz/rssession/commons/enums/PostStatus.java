package kz.rssession.commons.enums;

public enum PostStatus {
    DRAFT,     // создан, но ещё не опубликован
    ACTIVE,    // опубликован и виден пользователям
    ARCHIVED,  // скрыт, но сохранён
    DELETED    // логически удалён
}
