export interface PageDto<T> {
  content: T[];
  page?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;

  pageable: any;
}
