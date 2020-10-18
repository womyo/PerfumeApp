# 20171675 개인 과제 
## 첫번째 화면: 상품 선택 페이지
빵 종함세트와 최신형 오븐 두개의 상품을 이미지는 imageView에, 제품명과 가격은 textView에 담았습니다
상품을 선택하기 위해 checkbox를 사용하였습니다
상품을 선택한 후 장바구니 버튼을 누르면 장바구니 페이지로 이동되게 구현하였습니다.
MainActivity에서 setOnClickListener를 사용하여 checkBox가 선택되어있는 경우를 if문으로 나누었습니다
Intent를 이용하여 MainActivity와 secondActivity를 연결하여 intent.putExtra를 통해 선택한 상품의 정보와 이미지를 장바구니 페이지로 넘겨주었습니다
아무 상품도 선택하지 않은 경우에는 Toast.makeText에 장바구니에 넣을 상품을 하나 이상 선택하라는 메세지를 띄어줍니다. 그리고 다음 페이지로 넘어가지 않게 return문을 써 주었습니다.

