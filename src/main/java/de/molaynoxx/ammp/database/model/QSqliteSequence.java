package de.molaynoxx.ammp.database.model;

import static com.querydsl.core.types.PathMetadataFactory.*;
import de.molaynoxx.ammp.database.projection.SqliteSequence;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqliteSequence is a Querydsl query type for SqliteSequence
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSqliteSequence extends com.querydsl.sql.RelationalPathBase<SqliteSequence> {

    private static final long serialVersionUID = 750198656;

    public static final QSqliteSequence sqliteSequence = new QSqliteSequence("sqlite_sequence");

    public final StringPath name = createString("name");

    public final StringPath seq = createString("seq");

    public QSqliteSequence(String variable) {
        super(SqliteSequence.class, forVariable(variable), "null", "sqlite_sequence");
        addMetadata();
    }

    public QSqliteSequence(String variable, String schema, String table) {
        super(SqliteSequence.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqliteSequence(Path<? extends SqliteSequence> path) {
        super(path.getType(), path.getMetadata(), "null", "sqlite_sequence");
        addMetadata();
    }

    public QSqliteSequence(PathMetadata metadata) {
        super(SqliteSequence.class, metadata, "null", "sqlite_sequence");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(name, ColumnMetadata.named("name").withIndex(0).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
        addMetadata(seq, ColumnMetadata.named("seq").withIndex(1).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
    }

}

