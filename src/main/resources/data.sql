insert into author (name, thumbnail)
values ('author-1', null),
       ('author-2', 'https://thumb.nail.author/author-2'),
       ('author-3', 'https://thumb.nail.author/author-3'),
       ('author-4', 'https://thumb.nail.author/author-4');

insert into post (title, text, category, author_id)
values ('Post-1','Hello this is a dummy text','Cat-1', 1),
       ('Post-2','Hello Dummy Text 2', null, 1),
       ('Post-3','Hello Dummy Text 3', null, 2),
       ('Post-4','Hello Dummy Text 4', 'Cat-1', 3),
       ('Post-5','Hello Dummy Text 5', null, 3),
       ('Post-6','Hello Dummy Text 6', 'Cat-1', 3),
       ('Post-7','Hello Dummy Text 7', 'Cat-1', 4);
