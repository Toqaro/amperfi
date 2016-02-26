package de.molaynoxx.ammp.database.model;

import static com.querydsl.core.types.PathMetadataFactory.*;
import de.molaynoxx.ammp.database.projection.LibraryFolder;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QLibraryFolder is a Querydsl query type for LibraryFolder
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QLibraryFolder extends com.querydsl.sql.RelationalPathBase<LibraryFolder> {

    private static final long serialVersionUID = -709884138;

    public static final QLibraryFolder LibraryFolder = new QLibraryFolder("LibraryFolder");

    public final StringPath path = createString("path");

    public final com.querydsl.sql.PrimaryKey<LibraryFolder> libraryFolderPK = createPrimaryKey(path);

    public QLibraryFolder(String variable) {
        super(LibraryFolder.class, forVariable(variable), "null", "LibraryFolder");
        addMetadata();
    }

    public QLibraryFolder(String variable, String schema, String table) {
        super(LibraryFolder.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QLibraryFolder(Path<? extends LibraryFolder> path) {
        super(path.getType(), path.getMetadata(), "null", "LibraryFolder");
        addMetadata();
    }

    public QLibraryFolder(PathMetadata metadata) {
        super(LibraryFolder.class, metadata, "null", "LibraryFolder");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(path, ColumnMetadata.named("path").withIndex(0).ofType(Types.VARCHAR).withSize(2000000000).withDigits(10));
    }

}

