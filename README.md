# ItemUtility

서버의 아이템을 쉽게 관리하자!

## 사용방법
### 명령어
#### 아이템 추가

```
/iu create <itemname> <code> <data>
```

#### 아이템 삭제

```
/iu delete <itemname>
```

#### 아이템 코드 설정

```
/iu item <itemname> <code> <data>
```

#### 아이템 표시이름(display) 설정

```
/iu display <itemname> <text>
```

#### 아이템 설명 추가

```
/iu loreadd <itemname> <text>
```

#### 아이템 설명 제거

```
/iu loredel <itemname> <line>
```

#### 아이템 설명 설정

```
/iu loreset <itemname> <line> <text>
```

#### 아이템 인첸트 추가

```
/iu enchant <itemname> <enchant> <power>
```

#### 손에 들고있는 아이템 추가하기

```
/iu hand <itemname>
```

#### 아이템 목록

```
/iu list
```

#### 플러그인 리로드

```
/iu reload
```

### 펄미션

```
itemutility.*
```

### 인첸트 이름 목록

https://hub.spigotmc.org/javadocs/spigot/org/bukkit/enchantments/Enchantment.html

## API

ItemUtility에서는 ItemStack으로 반환해주는 API를 제공하고 있습니다.

```
ItemUtility.getItemUtilityItem(String itemname)
```

## 다운로드

GitHub Releases https://github.com/SkyLightQP/ItemUtility/releases

## 라이센스

ItemUtility 은(는) MIT 라이센스를 따르고 있습니다.

## 제작자

하늘빛QP(SkyLightQP)

http://skylightqp.kr
http://blog.skylightqp.kr
