PGDMP     8    .            
    v           dbautoescola    10.5    10.5 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    16393    dbautoescola    DATABASE     �   CREATE DATABASE dbautoescola WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE dbautoescola;
             postgres    false                        2615    16550    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16587    aluno    TABLE       CREATE TABLE public.aluno (
    matricula integer NOT NULL,
    nome character varying(50) NOT NULL,
    cpf character varying(14) NOT NULL,
    car_data_de_emissao character varying NOT NULL,
    car_orgao_expedidor character varying(10) NOT NULL,
    car_numero character varying(12) NOT NULL,
    data_nasc character varying NOT NULL,
    sexo character(1) NOT NULL,
    end_cep character varying(10) NOT NULL,
    end_rua character varying(50) NOT NULL,
    end_numero character varying(6) NOT NULL,
    end_bairro character varying(50) NOT NULL,
    end_cidade character varying(50) NOT NULL,
    tel_telefone_1 character varying(15) DEFAULT '0'::character varying,
    tel_telefone_2 character varying(15) DEFAULT '0'::character varying,
    tipo_habilitacao character(2) NOT NULL
);
    DROP TABLE public.aluno;
       public         postgres    false    4            �            1259    16585    aluno_matricula_seq    SEQUENCE     �   CREATE SEQUENCE public.aluno_matricula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.aluno_matricula_seq;
       public       postgres    false    4    204            �           0    0    aluno_matricula_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.aluno_matricula_seq OWNED BY public.aluno.matricula;
            public       postgres    false    203            �            1259    16570 	   atendente    TABLE     O   CREATE TABLE public.atendente (
    cpf_func character varying(14) NOT NULL
);
    DROP TABLE public.atendente;
       public         postgres    false    4            �            1259    16626    aulas_praticas    TABLE     [  CREATE TABLE public.aulas_praticas (
    id_aulas_praticas integer NOT NULL,
    quilometragem_final numeric(8,0) NOT NULL,
    quilometragem_inicial numeric(8,0) NOT NULL,
    horario_de_inicio character varying(5) NOT NULL,
    horario_de_termino character varying(5) NOT NULL,
    data_aula date NOT NULL,
    id_conj_aulas integer NOT NULL
);
 "   DROP TABLE public.aulas_praticas;
       public         postgres    false    4            �            1259    16624 $   aulas_praticas_id_aulas_praticas_seq    SEQUENCE     �   CREATE SEQUENCE public.aulas_praticas_id_aulas_praticas_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.aulas_praticas_id_aulas_praticas_seq;
       public       postgres    false    4    211            �           0    0 $   aulas_praticas_id_aulas_praticas_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.aulas_praticas_id_aulas_praticas_seq OWNED BY public.aulas_praticas.id_aulas_praticas;
            public       postgres    false    210            �            1259    16554    auto_escola    TABLE     �  CREATE TABLE public.auto_escola (
    id_aut integer NOT NULL,
    nome character varying(50) NOT NULL,
    matriz_ou_filial character varying(7) NOT NULL,
    telefone1 character varying(15) NOT NULL,
    end_cep character varying(10) NOT NULL,
    end_cidade character varying(50) NOT NULL,
    end_rua character varying(50) NOT NULL,
    end_numero character varying(6) NOT NULL,
    end_bairro character varying(50) NOT NULL,
    telefone2 character varying(15)
);
    DROP TABLE public.auto_escola;
       public         postgres    false    4            �            1259    16552    auto_escola_id_aut_seq    SEQUENCE     �   CREATE SEQUENCE public.auto_escola_id_aut_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.auto_escola_id_aut_seq;
       public       postgres    false    4    197            �           0    0    auto_escola_id_aut_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.auto_escola_id_aut_seq OWNED BY public.auto_escola.id_aut;
            public       postgres    false    196            �            1259    16674    avaliado    TABLE     �   CREATE TABLE public.avaliado (
    matricula integer NOT NULL,
    id_teorica integer NOT NULL,
    nota real NOT NULL,
    resultado character(1) NOT NULL
);
    DROP TABLE public.avaliado;
       public         postgres    false    4            �            1259    16616    conjunto_aulas    TABLE     l  CREATE TABLE public.conjunto_aulas (
    id_conj_aulas integer NOT NULL,
    matricula integer NOT NULL,
    data_de_inicio character varying NOT NULL,
    numero_de_aulas numeric(2,0) NOT NULL,
    hora_inicio character varying(5) NOT NULL,
    hora_fim character varying(5) NOT NULL,
    id_curso integer NOT NULL,
    cpf_func character varying(14) NOT NULL
);
 "   DROP TABLE public.conjunto_aulas;
       public         postgres    false    4            �            1259    16614     conjunto_aulas_id_conj_aulas_seq    SEQUENCE     �   CREATE SEQUENCE public.conjunto_aulas_id_conj_aulas_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.conjunto_aulas_id_conj_aulas_seq;
       public       postgres    false    4    209            �           0    0     conjunto_aulas_id_conj_aulas_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.conjunto_aulas_id_conj_aulas_seq OWNED BY public.conjunto_aulas.id_conj_aulas;
            public       postgres    false    208            �            1259    16597    cursos    TABLE       CREATE TABLE public.cursos (
    id_curso bigint NOT NULL,
    nome character varying(80) NOT NULL,
    carga_horaria_exigida numeric(3,0) NOT NULL,
    resumo_conteudo character varying(1000) NOT NULL,
    ch_curso_teorico numeric(3,0),
    ch_curso_pratico numeric(3,0)
);
    DROP TABLE public.cursos;
       public         postgres    false    4            �            1259    16595    cursos_id_curso_seq    SEQUENCE     |   CREATE SEQUENCE public.cursos_id_curso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.cursos_id_curso_seq;
       public       postgres    false    4    206            �           0    0    cursos_id_curso_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.cursos_id_curso_seq OWNED BY public.cursos.id_curso;
            public       postgres    false    205            �            1259    16575    diretor    TABLE     �   CREATE TABLE public.diretor (
    cpf_func character varying(14) NOT NULL,
    funcao character varying(20) NOT NULL,
    capacitacao character varying(100) NOT NULL
);
    DROP TABLE public.diretor;
       public         postgres    false    4            �            1259    16565    funcionario    TABLE     t  CREATE TABLE public.funcionario (
    cpf_func character varying(14) NOT NULL,
    car_data_de_emissao character varying NOT NULL,
    car_orgao_expedidor character varying(10) NOT NULL,
    car_numero character varying(10) NOT NULL,
    nome character varying(50) NOT NULL,
    data_nascimento character varying NOT NULL,
    sexo character(1) NOT NULL,
    data_de_admissao character varying NOT NULL,
    data_de_demissao character varying,
    tel_telefone1 character varying(15),
    tel_telefone2 character varying(15),
    e_mail character varying(50) NOT NULL,
    nivel_escolaridade character varying(100) NOT NULL,
    end_cep character varying(10) NOT NULL,
    end_cidade character varying(50) NOT NULL,
    end_rua character varying(80) NOT NULL,
    end_numero character varying(6) NOT NULL,
    end_bairro character varying(20) NOT NULL,
    id_aut integer NOT NULL
);
    DROP TABLE public.funcionario;
       public         postgres    false    4            �            1259    16580 	   instrutor    TABLE     �   CREATE TABLE public.instrutor (
    cpf_func character varying(14) NOT NULL,
    car_categorias character(1) NOT NULL,
    car_numero_registro numeric(11,0) NOT NULL,
    capacitacao character varying(1000) NOT NULL,
    tipo character varying
);
    DROP TABLE public.instrutor;
       public         postgres    false    4            �            1259    16684    matriculado    TABLE     �   CREATE TABLE public.matriculado (
    matricula integer NOT NULL,
    data_da_matricula character varying NOT NULL,
    numero_de_aulas numeric(2,0) NOT NULL,
    id_curso integer NOT NULL
);
    DROP TABLE public.matriculado;
       public         postgres    false    4            �            1259    16689    oferece    TABLE     \   CREATE TABLE public.oferece (
    id_aut integer NOT NULL,
    id_curso integer NOT NULL
);
    DROP TABLE public.oferece;
       public         postgres    false    4            �            1259    16935 	   permissao    TABLE     �   CREATE TABLE public.permissao (
    nome_user character varying(10) NOT NULL,
    senha_user character varying(100) NOT NULL,
    cpf_func character varying(14),
    id_aut integer,
    tipo_acesso integer,
    acesso_login character varying(20)
);
    DROP TABLE public.permissao;
       public         postgres    false    4            �            1259    16660    prova_pratica    TABLE     �   CREATE TABLE public.prova_pratica (
    id_pratica integer NOT NULL,
    local character varying(80) NOT NULL,
    data character varying NOT NULL,
    horario character varying(5) NOT NULL
);
 !   DROP TABLE public.prova_pratica;
       public         postgres    false    4            �            1259    16658    prova_pratica_id_pratica_seq    SEQUENCE     �   CREATE SEQUENCE public.prova_pratica_id_pratica_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.prova_pratica_id_pratica_seq;
       public       postgres    false    4    213            �           0    0    prova_pratica_id_pratica_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.prova_pratica_id_pratica_seq OWNED BY public.prova_pratica.id_pratica;
            public       postgres    false    212            �            1259    16668    prova_teorica    TABLE     �   CREATE TABLE public.prova_teorica (
    local character varying(20) NOT NULL,
    id_teorica integer NOT NULL,
    data character varying NOT NULL,
    horario character varying(5) NOT NULL
);
 !   DROP TABLE public.prova_teorica;
       public         postgres    false    4            �            1259    16666    prova_teorica_id_teorica_seq    SEQUENCE     �   CREATE SEQUENCE public.prova_teorica_id_teorica_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.prova_teorica_id_teorica_seq;
       public       postgres    false    4    215            �           0    0    prova_teorica_id_teorica_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.prova_teorica_id_teorica_seq OWNED BY public.prova_teorica.id_teorica;
            public       postgres    false    214            �            1259    16694 	   submetido    TABLE     �   CREATE TABLE public.submetido (
    matricula integer NOT NULL,
    id_pratica integer NOT NULL,
    placa character(7) NOT NULL,
    resultado character(1) NOT NULL
);
    DROP TABLE public.submetido;
       public         postgres    false    4            �            1259    16608    turmas_teoricas    TABLE     �   CREATE TABLE public.turmas_teoricas (
    horario character varying(14) NOT NULL,
    numero_maximo_de_alunos numeric(2,0) NOT NULL,
    id_curso integer NOT NULL,
    cpf_func character varying(14) NOT NULL,
    id_turmas integer NOT NULL
);
 #   DROP TABLE public.turmas_teoricas;
       public         postgres    false    4            �            1259    17002    usado    TABLE     �   CREATE TABLE public.usado (
    id_conj_aulas integer NOT NULL,
    placa character varying(7) NOT NULL,
    data character varying NOT NULL
);
    DROP TABLE public.usado;
       public         postgres    false    4            �            1259    16560    veiculos    TABLE     �   CREATE TABLE public.veiculos (
    modelo character(35) NOT NULL,
    placa character(7) NOT NULL,
    tipo character(1) NOT NULL,
    id_aut integer NOT NULL,
    ano character varying
);
    DROP TABLE public.veiculos;
       public         postgres    false    4            �
           2604    16590    aluno matricula    DEFAULT     r   ALTER TABLE ONLY public.aluno ALTER COLUMN matricula SET DEFAULT nextval('public.aluno_matricula_seq'::regclass);
 >   ALTER TABLE public.aluno ALTER COLUMN matricula DROP DEFAULT;
       public       postgres    false    203    204    204            �
           2604    16629     aulas_praticas id_aulas_praticas    DEFAULT     �   ALTER TABLE ONLY public.aulas_praticas ALTER COLUMN id_aulas_praticas SET DEFAULT nextval('public.aulas_praticas_id_aulas_praticas_seq'::regclass);
 O   ALTER TABLE public.aulas_praticas ALTER COLUMN id_aulas_praticas DROP DEFAULT;
       public       postgres    false    211    210    211            �
           2604    16557    auto_escola id_aut    DEFAULT     x   ALTER TABLE ONLY public.auto_escola ALTER COLUMN id_aut SET DEFAULT nextval('public.auto_escola_id_aut_seq'::regclass);
 A   ALTER TABLE public.auto_escola ALTER COLUMN id_aut DROP DEFAULT;
       public       postgres    false    197    196    197            �
           2604    16619    conjunto_aulas id_conj_aulas    DEFAULT     �   ALTER TABLE ONLY public.conjunto_aulas ALTER COLUMN id_conj_aulas SET DEFAULT nextval('public.conjunto_aulas_id_conj_aulas_seq'::regclass);
 K   ALTER TABLE public.conjunto_aulas ALTER COLUMN id_conj_aulas DROP DEFAULT;
       public       postgres    false    209    208    209            �
           2604    16600    cursos id_curso    DEFAULT     r   ALTER TABLE ONLY public.cursos ALTER COLUMN id_curso SET DEFAULT nextval('public.cursos_id_curso_seq'::regclass);
 >   ALTER TABLE public.cursos ALTER COLUMN id_curso DROP DEFAULT;
       public       postgres    false    206    205    206            �
           2604    16663    prova_pratica id_pratica    DEFAULT     �   ALTER TABLE ONLY public.prova_pratica ALTER COLUMN id_pratica SET DEFAULT nextval('public.prova_pratica_id_pratica_seq'::regclass);
 G   ALTER TABLE public.prova_pratica ALTER COLUMN id_pratica DROP DEFAULT;
       public       postgres    false    212    213    213            �
           2604    16671    prova_teorica id_teorica    DEFAULT     �   ALTER TABLE ONLY public.prova_teorica ALTER COLUMN id_teorica SET DEFAULT nextval('public.prova_teorica_id_teorica_seq'::regclass);
 G   ALTER TABLE public.prova_teorica ALTER COLUMN id_teorica DROP DEFAULT;
       public       postgres    false    215    214    215            �          0    16587    aluno 
   TABLE DATA               �   COPY public.aluno (matricula, nome, cpf, car_data_de_emissao, car_orgao_expedidor, car_numero, data_nasc, sexo, end_cep, end_rua, end_numero, end_bairro, end_cidade, tel_telefone_1, tel_telefone_2, tipo_habilitacao) FROM stdin;
    public       postgres    false    204   ��       �          0    16570 	   atendente 
   TABLE DATA               -   COPY public.atendente (cpf_func) FROM stdin;
    public       postgres    false    200   y�       �          0    16626    aulas_praticas 
   TABLE DATA               �   COPY public.aulas_praticas (id_aulas_praticas, quilometragem_final, quilometragem_inicial, horario_de_inicio, horario_de_termino, data_aula, id_conj_aulas) FROM stdin;
    public       postgres    false    211   ��       �          0    16554    auto_escola 
   TABLE DATA               �   COPY public.auto_escola (id_aut, nome, matriz_ou_filial, telefone1, end_cep, end_cidade, end_rua, end_numero, end_bairro, telefone2) FROM stdin;
    public       postgres    false    197   ��       �          0    16674    avaliado 
   TABLE DATA               J   COPY public.avaliado (matricula, id_teorica, nota, resultado) FROM stdin;
    public       postgres    false    216   ��       �          0    16616    conjunto_aulas 
   TABLE DATA               �   COPY public.conjunto_aulas (id_conj_aulas, matricula, data_de_inicio, numero_de_aulas, hora_inicio, hora_fim, id_curso, cpf_func) FROM stdin;
    public       postgres    false    209   ë       �          0    16597    cursos 
   TABLE DATA               |   COPY public.cursos (id_curso, nome, carga_horaria_exigida, resumo_conteudo, ch_curso_teorico, ch_curso_pratico) FROM stdin;
    public       postgres    false    206   T�       �          0    16575    diretor 
   TABLE DATA               @   COPY public.diretor (cpf_func, funcao, capacitacao) FROM stdin;
    public       postgres    false    201   ��       �          0    16565    funcionario 
   TABLE DATA                 COPY public.funcionario (cpf_func, car_data_de_emissao, car_orgao_expedidor, car_numero, nome, data_nascimento, sexo, data_de_admissao, data_de_demissao, tel_telefone1, tel_telefone2, e_mail, nivel_escolaridade, end_cep, end_cidade, end_rua, end_numero, end_bairro, id_aut) FROM stdin;
    public       postgres    false    199   ��       �          0    16580 	   instrutor 
   TABLE DATA               e   COPY public.instrutor (cpf_func, car_categorias, car_numero_registro, capacitacao, tipo) FROM stdin;
    public       postgres    false    202   X�       �          0    16684    matriculado 
   TABLE DATA               ^   COPY public.matriculado (matricula, data_da_matricula, numero_de_aulas, id_curso) FROM stdin;
    public       postgres    false    217   s�       �          0    16689    oferece 
   TABLE DATA               3   COPY public.oferece (id_aut, id_curso) FROM stdin;
    public       postgres    false    218   �       �          0    16935 	   permissao 
   TABLE DATA               g   COPY public.permissao (nome_user, senha_user, cpf_func, id_aut, tipo_acesso, acesso_login) FROM stdin;
    public       postgres    false    220   '�       �          0    16660    prova_pratica 
   TABLE DATA               I   COPY public.prova_pratica (id_pratica, local, data, horario) FROM stdin;
    public       postgres    false    213   �       �          0    16668    prova_teorica 
   TABLE DATA               I   COPY public.prova_teorica (local, id_teorica, data, horario) FROM stdin;
    public       postgres    false    215   E�       �          0    16694 	   submetido 
   TABLE DATA               L   COPY public.submetido (matricula, id_pratica, placa, resultado) FROM stdin;
    public       postgres    false    219   ��       �          0    16608    turmas_teoricas 
   TABLE DATA               j   COPY public.turmas_teoricas (horario, numero_maximo_de_alunos, id_curso, cpf_func, id_turmas) FROM stdin;
    public       postgres    false    207   �       �          0    17002    usado 
   TABLE DATA               ;   COPY public.usado (id_conj_aulas, placa, data) FROM stdin;
    public       postgres    false    221   E�       �          0    16560    veiculos 
   TABLE DATA               D   COPY public.veiculos (modelo, placa, tipo, id_aut, ano) FROM stdin;
    public       postgres    false    198   ·       �           0    0    aluno_matricula_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.aluno_matricula_seq', 10, true);
            public       postgres    false    203            �           0    0 $   aulas_praticas_id_aulas_praticas_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.aulas_praticas_id_aulas_praticas_seq', 22, true);
            public       postgres    false    210            �           0    0    auto_escola_id_aut_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.auto_escola_id_aut_seq', 5, true);
            public       postgres    false    196            �           0    0     conjunto_aulas_id_conj_aulas_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.conjunto_aulas_id_conj_aulas_seq', 9, true);
            public       postgres    false    208            �           0    0    cursos_id_curso_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.cursos_id_curso_seq', 15, true);
            public       postgres    false    205            �           0    0    prova_pratica_id_pratica_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.prova_pratica_id_pratica_seq', 2, true);
            public       postgres    false    212            �           0    0    prova_teorica_id_teorica_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.prova_teorica_id_teorica_seq', 1, false);
            public       postgres    false    214            �
           2606    16769    conjunto_aulas fkfaz_id 
   CONSTRAINT     W   ALTER TABLE ONLY public.conjunto_aulas
    ADD CONSTRAINT fkfaz_id UNIQUE (matricula);
 A   ALTER TABLE ONLY public.conjunto_aulas DROP CONSTRAINT fkfaz_id;
       public         postgres    false    209            �
           2606    16574    atendente fkfun_ate_id 
   CONSTRAINT     Z   ALTER TABLE ONLY public.atendente
    ADD CONSTRAINT fkfun_ate_id PRIMARY KEY (cpf_func);
 @   ALTER TABLE ONLY public.atendente DROP CONSTRAINT fkfun_ate_id;
       public         postgres    false    200            �
           2606    16579    diretor fkfun_dir_id 
   CONSTRAINT     X   ALTER TABLE ONLY public.diretor
    ADD CONSTRAINT fkfun_dir_id PRIMARY KEY (cpf_func);
 >   ALTER TABLE ONLY public.diretor DROP CONSTRAINT fkfun_dir_id;
       public         postgres    false    201            �
           2606    16584    instrutor fkfun_ins_id 
   CONSTRAINT     Z   ALTER TABLE ONLY public.instrutor
    ADD CONSTRAINT fkfun_ins_id PRIMARY KEY (cpf_func);
 @   ALTER TABLE ONLY public.instrutor DROP CONSTRAINT fkfun_ins_id;
       public         postgres    false    202            �
           2606    16829    matriculado fkmat_alu_id 
   CONSTRAINT     ]   ALTER TABLE ONLY public.matriculado
    ADD CONSTRAINT fkmat_alu_id PRIMARY KEY (matricula);
 B   ALTER TABLE ONLY public.matriculado DROP CONSTRAINT fkmat_alu_id;
       public         postgres    false    217            �
           2606    16594    aluno id_aluno_id 
   CONSTRAINT     V   ALTER TABLE ONLY public.aluno
    ADD CONSTRAINT id_aluno_id PRIMARY KEY (matricula);
 ;   ALTER TABLE ONLY public.aluno DROP CONSTRAINT id_aluno_id;
       public         postgres    false    204            �
           2606    16631     aulas_praticas id_aulas_praticas 
   CONSTRAINT     m   ALTER TABLE ONLY public.aulas_praticas
    ADD CONSTRAINT id_aulas_praticas PRIMARY KEY (id_aulas_praticas);
 J   ALTER TABLE ONLY public.aulas_praticas DROP CONSTRAINT id_aulas_praticas;
       public         postgres    false    211            �
           2606    16559    auto_escola id_auto_escola_id 
   CONSTRAINT     _   ALTER TABLE ONLY public.auto_escola
    ADD CONSTRAINT id_auto_escola_id PRIMARY KEY (id_aut);
 G   ALTER TABLE ONLY public.auto_escola DROP CONSTRAINT id_auto_escola_id;
       public         postgres    false    197            �
           2606    16758    avaliado id_avaliado 
   CONSTRAINT     e   ALTER TABLE ONLY public.avaliado
    ADD CONSTRAINT id_avaliado PRIMARY KEY (id_teorica, matricula);
 >   ALTER TABLE ONLY public.avaliado DROP CONSTRAINT id_avaliado;
       public         postgres    false    216    216            �
           2606    16621 #   conjunto_aulas id_conjunto_aulas_id 
   CONSTRAINT     l   ALTER TABLE ONLY public.conjunto_aulas
    ADD CONSTRAINT id_conjunto_aulas_id PRIMARY KEY (id_conj_aulas);
 M   ALTER TABLE ONLY public.conjunto_aulas DROP CONSTRAINT id_conjunto_aulas_id;
       public         postgres    false    209            �
           2606    16605    cursos id_cursos_id 
   CONSTRAINT     W   ALTER TABLE ONLY public.cursos
    ADD CONSTRAINT id_cursos_id PRIMARY KEY (id_curso);
 =   ALTER TABLE ONLY public.cursos DROP CONSTRAINT id_cursos_id;
       public         postgres    false    206            �
           2606    16569    funcionario id_funcionario 
   CONSTRAINT     ^   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT id_funcionario PRIMARY KEY (cpf_func);
 D   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT id_funcionario;
       public         postgres    false    199                       2606    16851    oferece id_oferece 
   CONSTRAINT     ^   ALTER TABLE ONLY public.oferece
    ADD CONSTRAINT id_oferece PRIMARY KEY (id_curso, id_aut);
 <   ALTER TABLE ONLY public.oferece DROP CONSTRAINT id_oferece;
       public         postgres    false    218    218            �
           2606    16665    prova_pratica id_prova_pratica 
   CONSTRAINT     d   ALTER TABLE ONLY public.prova_pratica
    ADD CONSTRAINT id_prova_pratica PRIMARY KEY (id_pratica);
 H   ALTER TABLE ONLY public.prova_pratica DROP CONSTRAINT id_prova_pratica;
       public         postgres    false    213            �
           2606    16673    prova_teorica id_prova_teorica 
   CONSTRAINT     d   ALTER TABLE ONLY public.prova_teorica
    ADD CONSTRAINT id_prova_teorica PRIMARY KEY (id_teorica);
 H   ALTER TABLE ONLY public.prova_teorica DROP CONSTRAINT id_prova_teorica;
       public         postgres    false    215                       2606    16888    submetido id_submetido 
   CONSTRAINT     n   ALTER TABLE ONLY public.submetido
    ADD CONSTRAINT id_submetido PRIMARY KEY (placa, id_pratica, matricula);
 @   ALTER TABLE ONLY public.submetido DROP CONSTRAINT id_submetido;
       public         postgres    false    219    219    219            �
           2606    16564    veiculos id_veiculos 
   CONSTRAINT     U   ALTER TABLE ONLY public.veiculos
    ADD CONSTRAINT id_veiculos PRIMARY KEY (placa);
 >   ALTER TABLE ONLY public.veiculos DROP CONSTRAINT id_veiculos;
       public         postgres    false    198            	           2606    16948 %   permissao nomeUser_permissao_nomeUser 
   CONSTRAINT     l   ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT "nomeUser_permissao_nomeUser" PRIMARY KEY (nome_user);
 Q   ALTER TABLE ONLY public.permissao DROP CONSTRAINT "nomeUser_permissao_nomeUser";
       public         postgres    false    220            �
           2606    17013    turmas_teoricas pk_tur_pk 
   CONSTRAINT     ^   ALTER TABLE ONLY public.turmas_teoricas
    ADD CONSTRAINT pk_tur_pk PRIMARY KEY (id_turmas);
 C   ALTER TABLE ONLY public.turmas_teoricas DROP CONSTRAINT pk_tur_pk;
       public         postgres    false    207                       2606    17021    usado pk_usado 
   CONSTRAINT     ^   ALTER TABLE ONLY public.usado
    ADD CONSTRAINT pk_usado PRIMARY KEY (id_conj_aulas, placa);
 8   ALTER TABLE ONLY public.usado DROP CONSTRAINT pk_usado;
       public         postgres    false    221    221            �
           1259    16924    fkava_alu_ind    INDEX     G   CREATE INDEX fkava_alu_ind ON public.avaliado USING btree (matricula);
 !   DROP INDEX public.fkava_alu_ind;
       public         postgres    false    216            �
           1259    16932    fkconstituido_ind    INDEX     Q   CREATE INDEX fkconstituido_ind ON public.turmas_teoricas USING btree (id_curso);
 %   DROP INDEX public.fkconstituido_ind;
       public         postgres    false    207            �
           1259    16922    fkformado_ind    INDEX     Q   CREATE INDEX fkformado_ind ON public.aulas_praticas USING btree (id_conj_aulas);
 !   DROP INDEX public.fkformado_ind;
       public         postgres    false    211            �
           1259    17019    fki_cpf_func    INDEX     K   CREATE INDEX fki_cpf_func ON public.conjunto_aulas USING btree (cpf_func);
     DROP INDEX public.fki_cpf_func;
       public         postgres    false    209            
           1259    17027    fki_fk_conj_aulas_fk    INDEX     O   CREATE INDEX fki_fk_conj_aulas_fk ON public.usado USING btree (id_conj_aulas);
 (   DROP INDEX public.fki_fk_conj_aulas_fk;
       public         postgres    false    221                       1259    17033    fki_fk_veiculos_fk    INDEX     E   CREATE INDEX fki_fk_veiculos_fk ON public.usado USING btree (placa);
 &   DROP INDEX public.fki_fk_veiculos_fk;
       public         postgres    false    221                       1259    16954    fki_fkaut_per_fk    INDEX     H   CREATE INDEX fki_fkaut_per_fk ON public.permissao USING btree (id_aut);
 $   DROP INDEX public.fki_fkaut_per_fk;
       public         postgres    false    220            �
           1259    17011    fki_fkcurso_matric_fk    INDEX     Q   CREATE INDEX fki_fkcurso_matric_fk ON public.matriculado USING btree (id_curso);
 )   DROP INDEX public.fki_fkcurso_matric_fk;
       public         postgres    false    217                       1259    16946    fki_fkfun_per_fk    INDEX     J   CREATE INDEX fki_fkfun_per_fk ON public.permissao USING btree (cpf_func);
 $   DROP INDEX public.fki_fkfun_per_fk;
       public         postgres    false    220            �
           1259    16960    fki_fkid_curso_fk    INDEX     P   CREATE INDEX fki_fkid_curso_fk ON public.conjunto_aulas USING btree (id_curso);
 %   DROP INDEX public.fki_fkid_curso_fk;
       public         postgres    false    209            �
           1259    16928    fkofe_aut_ind    INDEX     C   CREATE INDEX fkofe_aut_ind ON public.oferece USING btree (id_aut);
 !   DROP INDEX public.fkofe_aut_ind;
       public         postgres    false    218            �
           1259    16934    fkproprietaria_ind    INDEX     I   CREATE INDEX fkproprietaria_ind ON public.veiculos USING btree (id_aut);
 &   DROP INDEX public.fkproprietaria_ind;
       public         postgres    false    198            �
           1259    16933    fksao_ministradas_ind    INDEX     U   CREATE INDEX fksao_ministradas_ind ON public.turmas_teoricas USING btree (cpf_func);
 )   DROP INDEX public.fksao_ministradas_ind;
       public         postgres    false    207                       1259    16931    fksub_alu_ind    INDEX     H   CREATE INDEX fksub_alu_ind ON public.submetido USING btree (matricula);
 !   DROP INDEX public.fksub_alu_ind;
       public         postgres    false    219                       1259    16930    fksub_pro_ind    INDEX     I   CREATE INDEX fksub_pro_ind ON public.submetido USING btree (id_pratica);
 !   DROP INDEX public.fksub_pro_ind;
       public         postgres    false    219            �
           1259    16926 	   fktem_ind    INDEX     C   CREATE INDEX fktem_ind ON public.funcionario USING btree (id_aut);
    DROP INDEX public.fktem_ind;
       public         postgres    false    199            $           2606    17022    usado fk_conj_aulas_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.usado
    ADD CONSTRAINT fk_conj_aulas_fk FOREIGN KEY (id_conj_aulas) REFERENCES public.conjunto_aulas(id_conj_aulas);
 @   ALTER TABLE ONLY public.usado DROP CONSTRAINT fk_conj_aulas_fk;
       public       postgres    false    209    221    2801                       2606    17014    conjunto_aulas fk_cpf_func_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.conjunto_aulas
    ADD CONSTRAINT fk_cpf_func_fk FOREIGN KEY (cpf_func) REFERENCES public.instrutor(cpf_func);
 G   ALTER TABLE ONLY public.conjunto_aulas DROP CONSTRAINT fk_cpf_func_fk;
       public       postgres    false    209    2787    202            %           2606    17028    usado fk_veiculos_fk    FK CONSTRAINT     w   ALTER TABLE ONLY public.usado
    ADD CONSTRAINT fk_veiculos_fk FOREIGN KEY (placa) REFERENCES public.veiculos(placa);
 >   ALTER TABLE ONLY public.usado DROP CONSTRAINT fk_veiculos_fk;
       public       postgres    false    2778    198    221            #           2606    16949    permissao fkaut_per_fk    FK CONSTRAINT     ~   ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT fkaut_per_fk FOREIGN KEY (id_aut) REFERENCES public.auto_escola(id_aut);
 @   ALTER TABLE ONLY public.permissao DROP CONSTRAINT fkaut_per_fk;
       public       postgres    false    197    2775    220                       2606    16763    avaliado fkava_alu_fk    FK CONSTRAINT     }   ALTER TABLE ONLY public.avaliado
    ADD CONSTRAINT fkava_alu_fk FOREIGN KEY (matricula) REFERENCES public.aluno(matricula);
 ?   ALTER TABLE ONLY public.avaliado DROP CONSTRAINT fkava_alu_fk;
       public       postgres    false    216    204    2789                       2606    16752    avaliado fkava_pro    FK CONSTRAINT     �   ALTER TABLE ONLY public.avaliado
    ADD CONSTRAINT fkava_pro FOREIGN KEY (id_teorica) REFERENCES public.prova_teorica(id_teorica);
 <   ALTER TABLE ONLY public.avaliado DROP CONSTRAINT fkava_pro;
       public       postgres    false    216    215    2808                       2606    16902     turmas_teoricas fkconstituido_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.turmas_teoricas
    ADD CONSTRAINT fkconstituido_fk FOREIGN KEY (id_curso) REFERENCES public.cursos(id_curso);
 J   ALTER TABLE ONLY public.turmas_teoricas DROP CONSTRAINT fkconstituido_fk;
       public       postgres    false    206    2791    207                       2606    17006    matriculado fkcurso_matric_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.matriculado
    ADD CONSTRAINT fkcurso_matric_fk FOREIGN KEY (id_curso) REFERENCES public.cursos(id_curso);
 G   ALTER TABLE ONLY public.matriculado DROP CONSTRAINT fkcurso_matric_fk;
       public       postgres    false    2791    217    206                       2606    16775    conjunto_aulas fkfaz_fk    FK CONSTRAINT        ALTER TABLE ONLY public.conjunto_aulas
    ADD CONSTRAINT fkfaz_fk FOREIGN KEY (matricula) REFERENCES public.aluno(matricula);
 A   ALTER TABLE ONLY public.conjunto_aulas DROP CONSTRAINT fkfaz_fk;
       public       postgres    false    209    204    2789                       2606    16736    aulas_praticas fkformado_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.aulas_praticas
    ADD CONSTRAINT fkformado_fk FOREIGN KEY (id_conj_aulas) REFERENCES public.conjunto_aulas(id_conj_aulas);
 E   ALTER TABLE ONLY public.aulas_praticas DROP CONSTRAINT fkformado_fk;
       public       postgres    false    2801    209    211                       2606    16722    atendente fkfun_ate_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.atendente
    ADD CONSTRAINT fkfun_ate_fk FOREIGN KEY (cpf_func) REFERENCES public.funcionario(cpf_func);
 @   ALTER TABLE ONLY public.atendente DROP CONSTRAINT fkfun_ate_fk;
       public       postgres    false    2781    199    200                       2606    16800    diretor fkfun_dir_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.diretor
    ADD CONSTRAINT fkfun_dir_fk FOREIGN KEY (cpf_func) REFERENCES public.funcionario(cpf_func);
 >   ALTER TABLE ONLY public.diretor DROP CONSTRAINT fkfun_dir_fk;
       public       postgres    false    199    201    2781                       2606    16814    instrutor fkfun_ins_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.instrutor
    ADD CONSTRAINT fkfun_ins_fk FOREIGN KEY (cpf_func) REFERENCES public.funcionario(cpf_func);
 @   ALTER TABLE ONLY public.instrutor DROP CONSTRAINT fkfun_ins_fk;
       public       postgres    false    199    2781    202            "           2606    16941    permissao fkfun_per_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT fkfun_per_fk FOREIGN KEY (cpf_func) REFERENCES public.funcionario(cpf_func);
 @   ALTER TABLE ONLY public.permissao DROP CONSTRAINT fkfun_per_fk;
       public       postgres    false    220    199    2781                       2606    16955    conjunto_aulas fkid_curso_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.conjunto_aulas
    ADD CONSTRAINT fkid_curso_fk FOREIGN KEY (id_curso) REFERENCES public.cursos(id_curso);
 F   ALTER TABLE ONLY public.conjunto_aulas DROP CONSTRAINT fkid_curso_fk;
       public       postgres    false    209    2791    206                       2606    16834    matriculado fkmat_alu_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.matriculado
    ADD CONSTRAINT fkmat_alu_fk FOREIGN KEY (matricula) REFERENCES public.aluno(matricula);
 B   ALTER TABLE ONLY public.matriculado DROP CONSTRAINT fkmat_alu_fk;
       public       postgres    false    217    2789    204                       2606    16856    oferece fkofe_aut_fk    FK CONSTRAINT     |   ALTER TABLE ONLY public.oferece
    ADD CONSTRAINT fkofe_aut_fk FOREIGN KEY (id_aut) REFERENCES public.auto_escola(id_aut);
 >   ALTER TABLE ONLY public.oferece DROP CONSTRAINT fkofe_aut_fk;
       public       postgres    false    218    2775    197                       2606    16845    oferece fkofe_cur    FK CONSTRAINT     x   ALTER TABLE ONLY public.oferece
    ADD CONSTRAINT fkofe_cur FOREIGN KEY (id_curso) REFERENCES public.cursos(id_curso);
 ;   ALTER TABLE ONLY public.oferece DROP CONSTRAINT fkofe_cur;
       public       postgres    false    206    2791    218                       2606    16916    veiculos fkproprietaria_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.veiculos
    ADD CONSTRAINT fkproprietaria_fk FOREIGN KEY (id_aut) REFERENCES public.auto_escola(id_aut);
 D   ALTER TABLE ONLY public.veiculos DROP CONSTRAINT fkproprietaria_fk;
       public       postgres    false    197    2775    198                       2606    16907 $   turmas_teoricas fksao_ministradas_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.turmas_teoricas
    ADD CONSTRAINT fksao_ministradas_fk FOREIGN KEY (cpf_func) REFERENCES public.instrutor(cpf_func);
 N   ALTER TABLE ONLY public.turmas_teoricas DROP CONSTRAINT fksao_ministradas_fk;
       public       postgres    false    2787    207    202            !           2606    16893    submetido fksub_alu_fk    FK CONSTRAINT     ~   ALTER TABLE ONLY public.submetido
    ADD CONSTRAINT fksub_alu_fk FOREIGN KEY (matricula) REFERENCES public.aluno(matricula);
 @   ALTER TABLE ONLY public.submetido DROP CONSTRAINT fksub_alu_fk;
       public       postgres    false    204    219    2789                        2606    16882    submetido fksub_pro_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.submetido
    ADD CONSTRAINT fksub_pro_fk FOREIGN KEY (id_pratica) REFERENCES public.prova_pratica(id_pratica);
 @   ALTER TABLE ONLY public.submetido DROP CONSTRAINT fksub_pro_fk;
       public       postgres    false    219    2806    213                       2606    16870    submetido fksub_vei    FK CONSTRAINT     v   ALTER TABLE ONLY public.submetido
    ADD CONSTRAINT fksub_vei FOREIGN KEY (placa) REFERENCES public.veiculos(placa);
 =   ALTER TABLE ONLY public.submetido DROP CONSTRAINT fksub_vei;
       public       postgres    false    2778    198    219                       2606    16809    funcionario fktem_fk    FK CONSTRAINT     |   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT fktem_fk FOREIGN KEY (id_aut) REFERENCES public.auto_escola(id_aut);
 >   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT fktem_fk;
       public       postgres    false    199    2775    197            �   �  x���Mr�6�5|
.�)��Rq�4ˣ�����p@���=GWYt�#�b}AR�UKm���2%�����̛�ˮ������~v�0)
�M�J���05��S��r���e^�J1��2:cFrA��T�J����]��T���6d�����n�I���V2祠�'#+mr�� o�3��j�\��w!�8��h�0c
�L�G��1�``�L(. c�d��������mck8��*��qX.pp��)r}��	�u�=6!�?8C�	m���.��QVp�,sm���@e��TD��g�Q#�Q$��J&�������D��c�I��zOR�ZHA� K�&v����NyQ*^��H��Cz�h���m]�zbh��-l>��+>��9�l�d!U^j9$Y�7!f��ѻ���3FT&�T�VļR��2�j��Mx���s��ִ���5�O-'O�o\�����?T)y�ŗ(q*T�T�~8�m���A����1�1�wم��A�Da��/u�zuB��Ч�%�]h��Ɲ7�I�M$���j,,J�ÍC�f��#HB�����W��S��A�w�TZf�o}�s!��E׆�P�Ӆ���J�]���2�ʲ/��N�m\ֺ|���C֎�:�ݻ�A�
����X.&O�r�Udk���>��K6����]���4�j@� .�H�5@�U�gՀ^�qr�Tq�.�O�����#%�)-���I#Bj59�����D��s)�s�3F����U��ʹ�s��d�Z�Q�*���jFu�d�G�@(���������q����xg7_��r�ŏ5�-^�e��J�=*]��-�e��m��5���V>-�	T$��WM^���[�C�'/-��������>�6���Rɵ��U^%%�l��\�ɹ�\�j1�Y��ʴ���,�{$/��(	�V0d���!y�����F�%I�����="���Ƈ��ɗ�)�ɶL̓u�����1�g����%�E�l��)���ut��^��:���T�#�
��]<}�ͯC�o�3��K�՞����{:��g���Ii�/p�	�\|H¡N�(>��sL}4pW��6v��qT8�XY���U�m�K�{�[N6��ѭS۽[��t�O��H����>��仈�Y�v�g�����50@��%��lه�c�!͘�8!"��������Wb�8Q՘����n�W�L0����������.�θ      �   '   x�342�311�325�54�2r�A\##]C#�=... v)      �   �   x�u�]n� �gs�T��`�,{�st�H��y���d&��b�Ut���za�jui]�b��m��P�;kv�D�Ń5?�	�❵8 G�6�;[>"��(�݁J|�
�%^D�:��{Q�I�xf�䦏��\°����
	���
��:T�M�^�;������G��^)~ct�xR��r\����}�qm���֥&ެ$|�GTK��7}T�U�"��O*�L2� ��`��QDý��Ϻ�g732o���7�ۋ�      �   �   x�e�=
1���)��"�1F�RT����������x���t�+�����(f��M�R�V�$>Cǚ�͝��!��7���rX��"����*���\�}l8��wjk��='����ƭtk����g�x��1_�,���G²��>Q���b#�������~���p����Rw��IT      �   0   x�3�4�42�t�22M9��L@"�@N��!P���(���� �*�      �   �   x���A� Dѵ�K`Ƙ��,��9jڨ�H]t�d=��]�	�	q��e�ʌ�M��4Q/��������.�T���+���6���q�W=Rj���im#R_%ޜ����s�,q�Ͼ���+��U8�      �   �  x��R�n1��O� ��l�2
B4���h��eЮ��[�6�"����b����JAG��n<c?���\'	l{�[��}��v�>�@qi�ǾO�C9M��4p��͑��\v�u���m-�#�Qk���k#�MH��n8�D6�vVr����c����Η�X97��"9(�+��}@R! �3gm�|�TM@O�q�B`N;s�I$$����c=�m�y�fS>��#ַ��V������/��O���wvb�����,��`�߽����bk�!J�?�kN��hԤ�H2�m�䢏� ;g�U���j���=N�y�[�y�Q�7(�R}�6��������^�Lb�8`������$�`��l�X~�����L���s�/����h�Ԙ����'M������      �   �   x����
�0 �g��v۝.�c�B(�{8J3�� 
�^�8
uE(񜲺q��9�Y��gfIs��O�!�[Lpɏ�0[\JqI��/�g$T�D](�.�Rm������i'L�0ŗ�HH̕F!����CO�>��>섌�;A<SN      �   �  x����n�6���Sh�.,�~vq=I&A�{���l�q8�E�dM�`�誋�f׭_����ؖ�d�^�6�?�s�5Pr���A��e��d:wWX�D
��@�Z2�d�fz.��
.K�ײ"�u��B�QrI��6�������j)u�̒\���Q0�u|1�M��h0���4"\���D�ܘ�_&�"W�r�H[�7&�X������5>���@�Z Br��7�&�Ջ�ZG��Bs��`�18����wS��6�`��zf�(y�C�X�}>�| ����TQ$N~��Rʞ|�r䨁"�uij�.P���-��?q�9��8� D�IVwz������_����$<朓/F�`���3kyo�0<yLn^�$�XRdB�%�X�e���%Km��,��W�T���� ��w�"����Bn�>�u� ϟ�1o�exx	Ghj���&�=TY?+]� ۫
��K#"+��0p�p��3�q8���*��e9�&�;��,�;4�0+�Zl+�ǒ6�>\�8�ˏ9L�MA24���Kam"�qQ(�u�8qSb�M2�!8$N|D.K�\]�\����8�w��	u��L����ǄʉN��&I�:�gF�(DkzƦĩ�� 4�0�RV%p��L���(t�Dd����WU���T�7 ����5&8VBD�S������?&�ڷ�RW���G�����9f��tփ6��N�ˇÐ�!��ڴ��*��+�)���f���{t�)�{5���ib�?���m�رr{���v�7�������&��*u�2`v�0#�Цw��m�I�y��lk���Z�M��G�ͣ���1*�4��hA������ߨ^p����*ճ�^ ެҖ��=��$N��܆���d��X�ޣ*�f�M�)�^���+���D��!���Sg)9T��I��fI��w�J��M0U�7��9��ڶ�
�	�"`
�%!�M庼#"�NLZ�#��)�����wbd��u>Q ;L�F'�sR]��j�On�A��냑�D�	�8ˍ��Rޙwo�(b	�����~Ԓp����J\\3��g>�L��-�42�����@���*�9��s"}�y�4�������".���";$�N{$ ��*J��V��.<�m�fa;�������9��9<;;��?      �     x���=n� �g|
� >��X�:��2fA6��Rc�ӡ�i��+�'R�4�7~�����i	�#,�'B*���:��ic����m8�>���6t�ut��㑴V�`���ڴ�n��Mpo�i��i@^�����&.*͸�Z�5Uh\Z�,�ӕx[,[:sp�:�K_W�!�n���E[�T��W�KI��*n1�L�.�{#OM'�Nx�	w�	�בEʌ���6��F1���m1�s��/��i�J�-޳,�~ �8\      �   l   x�eϻ� ��&H�]��!Nc��y���	�0��]X$k�*4�N�I�$/��"���[�*�53__%�f�~Ș��Bv���}�/����mR�T=e��O&�@ϥ�/j6�      �   (   x�3��2�4bC 6bS.#N3 6b 6����� hp�      �   �   x���Aj1��}�^ =ٖ��4ch]g�!�_dJ�'(����w�<{r��u��}T�&:cl5Vs��(�p�ٻM��Pg�Y�-ֱPá !q�}��կ�ac��k�T����	��t�����y�Nn$T�!64x݆�H�j2\��IF�;Ͻ�F�&j*$R��~�o���_5�?G      �   O   x�3�t,K��LITpN,.I��Wp*J�K��40�74�720��4��20�2¥��M�1.�F�h*Mp�4�74DV���� l?.+      �   <   x�u�u�4�44�7��720��4��20�
�q�c7�4ªބ��L�� Y<F��� ��      �   A   x�3�4��p6�03�t�2�4�tw6241��2�4�ttr6526��:9�f�@n� Yi
�      �   S   x�m���0�7L��0e�n���K+�~��m�d�Tm��� �сnUY�W�vVvRA@�.��6�����[��2��      �   m   x�M�1�0D�E�ƥD�I���?G�(�I���R�B����I���Z�B �s�h���9�Aa���:�Q|3�e���ϟ��Ҩ��#d�/�T���3 ��      �   �  x���[r�0�g��l��p�b�N/R/Ug���V�L;mwӵtc-��8�<����L�tK�ޏ[������8
VT�Mpâ
}2+2N��|�lMˑvDF}�'H��#.�P������2N�_�M �Ѭ5�!)r
�j�,��ѯÖ��K�i�I5(�Dvl��y��rř<V�/9Ev-��(���|���|��e�8��h�i4C�"�����+N��p�,���"���kСqFk)�wlE��-�����JEZ�[1�-������5>��	QI�2�XHwK��1]l�-/g�QW��6��ʐ��U�e�����]��PG#�K��g�[;}�k�A��L�mlVl���XM
A�iت@��ORA/�1�DU7Z�=c5� �ӱ�� �,zDI     